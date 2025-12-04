package com.project.codinviec.service.imp.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.codinviec.dto.InforEmailDTO;
import com.project.codinviec.exception.auth.*;
import com.project.codinviec.model.UserBlock;
import com.project.codinviec.dto.auth.RegisterDTO;
import com.project.codinviec.dto.auth.TokenDTO;
import com.project.codinviec.dto.auth.UserDTO;
import com.project.codinviec.entity.auth.Role;
import com.project.codinviec.entity.auth.User;
import com.project.codinviec.exception.common.NotFoundIdExceptionHandler;
import com.project.codinviec.mapper.auth.RegisterMapper;
import com.project.codinviec.mapper.auth.UserMapper;
import com.project.codinviec.request.auth.GoogleUserRequest;
import com.project.codinviec.repository.auth.AccessTokenRepository;
import com.project.codinviec.repository.auth.RefreshTokenRepository;
import com.project.codinviec.repository.auth.RoleRepository;
import com.project.codinviec.repository.auth.UserRepository;
import com.project.codinviec.request.auth.LoginRequest;
import com.project.codinviec.request.auth.RegisterRequest;
import com.project.codinviec.request.auth.UpdateProfileRequest;
import com.project.codinviec.service.auth.AuthService;
import com.project.codinviec.util.helper.BlockUserHelper;
import com.project.codinviec.util.helper.KafkaHelper;
import com.project.codinviec.util.helper.TimeHelper;
import com.project.codinviec.util.security.JWTHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenRepository accessTokenRepository;
    private final JWTHelper jwtHelper;

    private final BlockUserHelper blockUserHelper;

    @Qualifier("redisTemplateDb0")
    private final RedisTemplate<String, String> redisTemplateDb00;

    private final TimeHelper timeHelper;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RegisterMapper registerMapper;
    private final RoleRepository roleRepository;
    private final KafkaHelper kafkaHelper;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public TokenDTO login(LoginRequest loginRequest) {
        ObjectMapper objectMapper = new ObjectMapper();

        // kiểm tra block

        try {
            if (redisTemplateDb00.hasKey(loginRequest.getEmail())) {
                String json = redisTemplateDb00.opsForValue().get(loginRequest.getEmail());
                UserBlock userBlock = objectMapper.readValue(json, UserBlock.class);
                if (userBlock.isBlocked()) {
                    String stringTime = timeHelper
                            .parseLocalDateTimeToSimpleTime(LocalDateTime.parse(userBlock.getExpireTime()));
                    throw new BlockLoginUserExceptionHandler(stringTime);
                }
            }

        } catch (BlockLoginUserExceptionHandler e){
            throw e;
        }
        catch (Exception ex) {
            throw new LoginFaildExceptionHandler();
        }

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> {
                    // điếm số lần nhập sai
                    blockUserHelper.updateCountErrorUser(loginRequest.getEmail());
                    return new WrongPasswordOrEmailExceptionHandler("Không tìm thấy email!");
                });

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            blockUserHelper.updateCountErrorUser(loginRequest.getEmail());
            throw new WrongPasswordOrEmailExceptionHandler("Tài khoản hoặc mật khẩu không hợp lệ!");
        }

        boolean isActiveAccessToken = accessTokenRepository.existsByUser_IdAndIsRevokedFalse(user.getId());
        boolean isActiveRefreshToken = refreshTokenRepository.existsByUser_IdAndIsRevokedFalse(user.getId());

        if (isActiveAccessToken || isActiveRefreshToken) {
            throw new AlreadyLoggedInExceptionHandler("Tài khoản này đã được đăng nhập ở nơi khác!");
        }

        String accessToken = jwtHelper.createAccessToken(user.getRole().getRoleName(), user.getId());
        String refershToken = jwtHelper.createRefershToken(user.getRole().getRoleName(), user.getId());

        return TokenDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refershToken)
                .build();
    }

    @Override
    @Transactional
    public TokenDTO refreshToken(String refreshToken) {
        String userId = jwtHelper.verifyRefreshToken(refreshToken);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Id User"));

        String newAccessToken = jwtHelper.createAccessToken(user.getRole().getRoleName(), user.getId());
        String newRefreshToken = jwtHelper.createRefershToken(user.getRole().getRoleName(), user.getId());

        return TokenDTO.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }

    @Override
    public RegisterDTO register(RegisterRequest registerRequest) {
        userRepository.findByEmail(registerRequest.getEmail())
                .ifPresent(user -> {
                    throw new EmailAlreadyExistsExceptionHandler("Email đã tồn tại!");
                });

        Role defaultRole = roleRepository.findByRoleNameIgnoreCase("USER")
                .orElseGet(() -> roleRepository.save(Role.builder()
                        .roleName("USER")
                        .createdDate(LocalDateTime.now())
                        .updatedDate(LocalDateTime.now())
                        .build()));

        User user = registerMapper.saveRegister(registerRequest, defaultRole);
        User savedUser = userRepository.save(user);
        if (!savedUser.getId().isBlank() && !savedUser.getId().isEmpty() && savedUser.getId() != null) {
            kafkaHelper.sendKafkaEmailRegister("register_email",
                    InforEmailDTO.builder()
                            .email(savedUser.getEmail())
                            .firstName(savedUser.getFirstName())
                            .dateCreated(timeHelper.parseLocalDateTimeToSimpleTime(savedUser.getCreatedDate()))
                            .build());
        }

        return registerMapper.toRegisterDTO(savedUser);
    }

    @Transactional
    @Override
    public void logout(String refreshToken) {
        if (refreshToken == null || refreshToken.isEmpty()) {
            throw new RefreshTokenExceptionHandler("Không tìm thấy Refresh Token!");
        }

        String userId = jwtHelper.verifyRefreshToken(refreshToken);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Id User"));

        accessTokenRepository.revokeAllAccessTokens(user.getId());
        refreshTokenRepository.revokeAllRefreshTokens(user.getId());
    }

    @Override
    @Transactional
    public TokenDTO googleLogin(GoogleUserRequest request) {
        // Tìm user theo email
        User user = userRepository.findByEmail(request.getEmail()).orElse(null);

        if (user == null) {
            // Nếu user chưa tồn tại, tạo user mới
            Role defaultRole = roleRepository.findByRoleNameIgnoreCase("USER")
                    .orElseGet(() -> roleRepository.save(Role.builder()
                            .roleName("USER")
                            .createdDate(LocalDateTime.now())
                            .updatedDate(LocalDateTime.now())
                            .build()));

            user = User.builder()
                    .email(request.getEmail())
                    .firstName(request.getFirstName() != null ? request.getFirstName() : "")
                    .lastName(request.getLastName() != null ? request.getLastName() : "")
                    .avatar(request.getPicture() != null ? request.getPicture() : "")
                    .password("") // OAuth user không có password
                    .role(defaultRole)
                    .isFindJob(false)
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now())
                    .build();

            user = userRepository.save(user);

            if (user.getId() != null && !user.getId().isBlank() && !user.getId().isEmpty()) {
                kafkaHelper.sendKafkaEmailRegister("register_email",
                        InforEmailDTO.builder()
                                .email(user.getEmail())
                                .firstName(user.getFirstName())
                                .dateCreated(timeHelper.parseLocalDateTimeToSimpleTime(user.getCreatedDate()))
                                .build());
            }
        } else {
            // Nếu user đã tồn tại, cập nhật thông tin từ Google (nếu cần)
            if (request.getFirstName() == null || request.getFirstName().isEmpty()) {
                user.setFirstName(request.getFirstName());
            }

            if (request.getLastName() == null || request.getLastName().isEmpty()) {
                user.setLastName(request.getLastName());
            }

            if (request.getPicture() == null || request.getPicture().isEmpty()) {
                user.setAvatar(request.getPicture());
            }

            user.setUpdatedDate(LocalDateTime.now());
            user = userRepository.save(user);
        }

        // Kiểm tra xem user có đang đăng nhập ở nơi khác không
        boolean isActiveAccessToken = accessTokenRepository.existsByUser_IdAndIsRevokedFalse(user.getId());
        boolean isActiveRefreshToken = refreshTokenRepository.existsByUser_IdAndIsRevokedFalse(user.getId());

        if (isActiveAccessToken || isActiveRefreshToken) {
            throw new AlreadyLoggedInExceptionHandler("Tài khoản này đã được đăng nhập ở nơi khác!");
        }

        // Tạo JWT tokens
        String accessToken = jwtHelper.createAccessToken(user.getRole().getRoleName(), user.getId());
        String refreshToken = jwtHelper.createRefershToken(user.getRole().getRoleName(), user.getId());

        return TokenDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public UserDTO getProfile(String userId) {
        // Validate userId
        if (userId == null || userId.isEmpty()) {
            throw new AccessTokenExceptionHandler("UserId không hợp lệ");
        }

        // Lấy thông tin user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy user với id: " + userId));

        return userMapper.userToUserDTO(user);
    }

    @Override
    @Transactional
    public UserDTO updateProfile(String userId, UpdateProfileRequest request) {
        // Validate userId
        if (userId == null || userId.isEmpty()) {
            throw new AccessTokenExceptionHandler("UserId không hợp lệ");
        }

        // Lấy user hiện tại
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy user với id: " + userId));

        // Cập nhật thông tin profile (không cho phép thay đổi email)
        userMapper.updateProfileMapper(user, request);

        // Lưu user đã cập nhật
        User updatedUser = userRepository.save(user);

        return userMapper.userToUserDTO(updatedUser);
    }
}
