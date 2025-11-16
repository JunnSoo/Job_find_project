package com.project.it_job.service.imp.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.it_job.block.UserBlock;
import com.project.it_job.dto.auth.RegisterDTO;
import com.project.it_job.dto.auth.TokenDTO;
import com.project.it_job.entity.auth.Role;
import com.project.it_job.entity.auth.User;
import com.project.it_job.exception.*;
import com.project.it_job.mapper.auth.RegisterMapper;
import com.project.it_job.repository.auth.AccessTokenRepository;
import com.project.it_job.repository.auth.RefreshTokenRepository;
import com.project.it_job.repository.auth.RoleRepository;
import com.project.it_job.repository.auth.UserRepository;
import com.project.it_job.request.auth.LoginRequest;
import com.project.it_job.request.auth.RegisterRequest;
import com.project.it_job.service.auth.AuthService;
import com.project.it_job.util.BlockUserHelpper;
import com.project.it_job.util.JWTHelpper;
import com.project.it_job.util.SendEmailHelpper;
import com.project.it_job.util.TimeHelpper;
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
    private final JWTHelpper jwtHelpper;

    private final BlockUserHelpper blockUserHelpper;

    @Qualifier("redisTemplateDb0")
    private final RedisTemplate<String, String> redisTemplateDb00;

    private final TimeHelpper timeHelpper;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RegisterMapper registerMapper;
    private final RoleRepository roleRepository;
    private final SendEmailHelpper sendEmailHelpper;


    @Override
    @Transactional
    public TokenDTO login(LoginRequest loginRequest) {
        ObjectMapper objectMapper = new ObjectMapper();

//        kiểm tra block
        String stringTime  = "";
        try {
            if (redisTemplateDb00.hasKey(loginRequest.getEmail())) {
                String json = redisTemplateDb00.opsForValue().get(loginRequest.getEmail());
                UserBlock userBlock = objectMapper.readValue(json, UserBlock.class);
                if (userBlock.isBlocked()){
                    stringTime = timeHelpper
                            .parseLocalDateTimeToSimpleTime(LocalDateTime.parse(userBlock.getExpireTime()));
                    throw new BlockLoginUserExceptionHandler(stringTime);
                }
            }
        } catch (Exception e) {
            throw new BlockLoginUserExceptionHandler(stringTime);
        }

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> {
//                    điếm số lần nhập sai
                    blockUserHelpper.updateCountErrorUser(loginRequest.getEmail());
                    return new EmailNotFoundExceptionHandler("Không tìm thấy email!");
                });

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            blockUserHelpper.updateCountErrorUser(loginRequest.getEmail());
            throw new WrongPasswordExceptionHandler("Mật khẩu không hợp lệ!");
        }

        boolean isActiveAccessToken = accessTokenRepository.existsByUser_IdAndIsRevokedFalse(user.getId());
        boolean isActiveRefreshToken = refreshTokenRepository.existsByUser_IdAndIsRevokedFalse(user.getId());

        if(isActiveAccessToken || isActiveRefreshToken) {
            throw new AlreadyLoggedInException("Tài khoản này đã được đăng nhập ở nơi khác!");
        }


        String accessToken = jwtHelpper.createAccessToken(user.getRole().getRoleName(), user.getId());
        String refershToken = jwtHelpper.createRefershToken(user.getRole().getRoleName(), user.getId());

        return TokenDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refershToken)
                .build();
    }

    @Override
    @Transactional
    public TokenDTO refreshToken(String refreshToken) {
        String userId = jwtHelpper.verifyRefreshToken(refreshToken);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Id User"));

        String newAccessToken = jwtHelpper.createAccessToken(user.getRole().getRoleName(),user.getId());
        String newRefreshToken = jwtHelpper.createRefershToken(user.getRole().getRoleName(),user.getId());

        return TokenDTO.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }

    @Override
    public RegisterDTO register(RegisterRequest registerRequest) {
        userRepository.existsByEmail(registerRequest.getEmail())
                .orElseThrow(() -> new EmailAlreadyExists("Email đã tồn tại!!"));

        Role defaultRole = roleRepository.findByRoleNameIgnoreCase("USER")
                .orElseGet(() -> roleRepository.save(Role.builder().roleName("USER").build()));

        User user = registerMapper.saveRegister(registerRequest, defaultRole);
        User savedUser = userRepository.save(user);
        if (!savedUser.getId().isBlank() && !savedUser.getId().isEmpty() && savedUser.getId() !=null){
            sendEmailHelpper.sendEmailRegister(savedUser);
        }
        return registerMapper.toRegisterDTO(savedUser);
    }

    @Transactional
    @Override
    public void logout(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("không tìm thấy id"));

        accessTokenRepository.revokeAllAccessTokens(user.getId());
        refreshTokenRepository.revokeAllRefreshTokens(user.getId());
    }
}