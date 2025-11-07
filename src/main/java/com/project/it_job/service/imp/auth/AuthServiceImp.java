package com.project.it_job.service.imp.auth;

import com.project.it_job.dto.auth.TokenDTO;
import com.project.it_job.entity.auth.AccessToken;
import com.project.it_job.entity.auth.User;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.repository.auth.AccessTokenRepository;
import com.project.it_job.repository.auth.UserRepository;
import com.project.it_job.service.auth.AuthService;
import com.project.it_job.util.JWTTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTTokenUtil jwtTokenUtil;
    private final AccessTokenRepository accessTokenRepository;

    @Override
    @Transactional

    public TokenDTO login(String email, String rawPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy user với email: " + email));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Sai mật khẩu!");
        }

        String accessToken = jwtTokenUtil.generateAccessToken(email);
        String refreshToken = jwtTokenUtil.generateRefreshToken(email);
        accessTokenRepository.deleteAllByUser(user);

        AccessToken entity = AccessToken.builder()
                .user(user)
                .token(accessToken)
                .isRevoked(false)
                .createDate(LocalDateTime.now())
                .build();

        accessTokenRepository.save(entity);
        return new TokenDTO(accessToken, refreshToken);
    }
    @Override
    @Transactional
    public void logout(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy user với email: " + email));

        // Cập nhật tất cả token của user thành isRevoked = true
        accessTokenRepository.revokeAllTokensByUser(user);
    }
}
