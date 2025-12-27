package com.project.codinviec.service.imp.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.codinviec.dto.InforEmailDTO;
import com.project.codinviec.dto.auth.*;
import com.project.codinviec.entity.auth.Role;
import com.project.codinviec.entity.auth.User;
import com.project.codinviec.exception.auth.*;
import com.project.codinviec.exception.common.NotFoundIdExceptionHandler;
import com.project.codinviec.exception.file.FileExceptionHandler;
import com.project.codinviec.mapper.auth.RegisterMapper;
import com.project.codinviec.mapper.auth.RoleMapper;
import com.project.codinviec.mapper.auth.UserMapper;
import com.project.codinviec.model.UserBlock;
import com.project.codinviec.repository.auth.RoleRepository;
import com.project.codinviec.repository.auth.UserRepository;
import com.project.codinviec.request.ChangeSoftSkillRequest;
import com.project.codinviec.request.UpdateAvatarRequest;
import com.project.codinviec.request.auth.GoogleUserRequest;
import com.project.codinviec.request.auth.LoginRequest;
import com.project.codinviec.request.auth.RegisterRequest;
import com.project.codinviec.request.auth.UpdateProfileRequest;
import com.project.codinviec.service.auth.AuthService;
import com.project.codinviec.service.auth.TokenManagerService;
import com.project.codinviec.service.file.FileService;
import com.project.codinviec.util.helper.BlockUserHelper;
import com.project.codinviec.util.helper.KafkaHelper;
import com.project.codinviec.util.helper.TimeHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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

    private final BlockUserHelper blockUserHelper;

    @Qualifier("redisTemplateDb0")
    private final RedisTemplate<String, String> redisTemplateDb00;
    private final ObjectMapper objectMapper;

    private final TimeHelper timeHelper;
    private final RegisterMapper registerMapper;
    private final RoleRepository roleRepository;
    private final KafkaHelper kafkaHelper;
    private final UserMapper userMapper;
    private final FileService fileService;

    private final TokenManagerService tokenManagerService;
    private final RoleMapper roleMapper;

    @Value("${upload.link}")
    private String linkBe;

    @Override
    @Transactional
    public TokenDTO login(LoginRequest loginRequest) {
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

//        check pass
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            blockUserHelper.updateCountErrorUser(loginRequest.getEmail());
            throw new WrongPasswordOrEmailExceptionHandler("Tài khoản hoặc mật khẩu không hợp lệ!");
        }

//        check block
        if (user.getIsBlock()){
            throw new BlockLoginUserExceptionHandler("Tài khoản bị khóa viễn vĩnh hãy liên hệ admin!");
        }

        AccessTokenDTO accessTokenDTO = tokenManagerService.getAccessToken(user.getId());
        RefreshTokenDTO refreshTokenDTO = tokenManagerService.getRefreshToken(user.getId());

        if (accessTokenDTO != null || refreshTokenDTO != null) {
            throw new AlreadyLoggedInExceptionHandler("Tài khoản này đã được đăng nhập ở nơi khác!");
        }

        String accessToken = tokenManagerService.createAccessToken(user.getRole().getRoleName(), user.getId());
        String refershToken = tokenManagerService.createRefreshToken(user.getRole().getRoleName(), user.getId());

        return TokenDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refershToken)
                .build();
    }

    @Override
    @Transactional
    public TokenDTO refreshToken(String refreshToken) {
        JwtUserDTO userJwt = tokenManagerService.verifyRefreshToken(refreshToken);

        User user = userRepository.findById(userJwt.getUserId())
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Id User"));

        String newAccessToken = tokenManagerService.createAccessToken(user.getRole().getRoleName(), user.getId());
        String newRefreshToken = tokenManagerService.createRefreshToken(user.getRole().getRoleName(), user.getId());

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

        JwtUserDTO userJwt = tokenManagerService.verifyRefreshToken(refreshToken);

        User user = userRepository.findById(userJwt.getUserId())
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Id User"));

        tokenManagerService.revokeAllTokens(user.getId());
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
        AccessTokenDTO accessTokenDTO = tokenManagerService.getAccessToken(user.getId());
        RefreshTokenDTO refreshTokenDTO = tokenManagerService.getRefreshToken(user.getId());

        if (accessTokenDTO != null || refreshTokenDTO != null) {
            throw new AlreadyLoggedInExceptionHandler("Tài khoản này đã được đăng nhập ở nơi khác!");
        }

        String accessToken = tokenManagerService.createAccessToken(user.getRole().getRoleName(), user.getId());
        String refreshToken = tokenManagerService.createRefreshToken(user.getRole().getRoleName(), user.getId());

        return TokenDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public ProfileDTO getProfile(String userId) {
        // Validate userId
        if (userId == null || userId.isEmpty()) {
            throw new AccessTokenExceptionHandler("UserId không hợp lệ");
        }

        // Lấy thông tin user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy user với id: " + userId));

        ProfileDTO updatedUser = userMapper.userToProfileDTO(user);
        updatedUser.setRole(roleMapper.toRoleDTO(user.getRole()));

        return updatedUser;
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
        UserDTO userDTO = userMapper.userToUserDTO(updatedUser);
        userDTO.setRole(roleMapper.toRoleDTO(updatedUser.getRole()));
        return userDTO;
    }

    @Override
    public UserDTO toggleIsFindJob(String userId) {
        // Lấy user hiện tại
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy user với id: " + userId));
        user.setIsFindJob(!user.getIsFindJob());
        User updatedUser = userRepository.save(user);
        UserDTO userDTO = userMapper.userToUserDTO(updatedUser);
        userDTO.setRole(roleMapper.toRoleDTO(updatedUser.getRole()));
        return userDTO;
    }

    @Override
    public UserDTO changeSoftSkill(String userId, ChangeSoftSkillRequest changeSoftSkillRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy user với id: " + userId));
        user.setGroupSoftSkill(changeSoftSkillRequest.getSoftSkill());
        User updatedUser = userRepository.save(user);
        UserDTO userDTO = userMapper.userToUserDTO(updatedUser);
        userDTO.setRole(roleMapper.toRoleDTO(updatedUser.getRole()));
        return userDTO;
    }

    @Override
    public UserDTO updateAvatar(String userId, UpdateAvatarRequest updateAvatarRequest) {
        // Validate userId
        if (userId == null || userId.isEmpty()) {
            throw new AccessTokenExceptionHandler("UserId không hợp lệ");
        }

        // Lấy user hiện tại
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy user với id: " + userId));

        if (user.getAvatar() != null && !user.getAvatar().isEmpty() && user.getAvatar().contains(linkBe)) {
            String nameAvatarFileOld = user.getAvatar().replaceFirst(linkBe+"/","");
            fileService.deleteFile(nameAvatarFileOld);
        }

        String nameAvatarFile = fileService.saveFiles(updateAvatarRequest.getAvatarFile());
        if (nameAvatarFile == null) {
           throw new FileExceptionHandler("Cập nhật thất bại avatar của user với id: " + userId);
        }
        user.setAvatar(linkBe + "/" + nameAvatarFile);

        User updatedUser = userRepository.save(user);
        UserDTO userDTO = userMapper.userToUserDTO(updatedUser);
        userDTO.setRole(roleMapper.toRoleDTO(updatedUser.getRole()));
        return userDTO;
    }
}
