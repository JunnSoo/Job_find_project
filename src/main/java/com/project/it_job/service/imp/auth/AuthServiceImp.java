package com.project.it_job.service.imp.auth;

import com.project.it_job.dto.auth.RegisterDTO;
import com.project.it_job.dto.auth.TokenDTO;
import com.project.it_job.entity.auth.AccessToken;
import com.project.it_job.entity.auth.Role;
import com.project.it_job.entity.auth.User;
import com.project.it_job.exception.ConflictException;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.auth.RegisterMapper;
import com.project.it_job.repository.auth.AccessTokenRepository;
import com.project.it_job.repository.auth.RoleRepository;
import com.project.it_job.repository.auth.UserRepository;
import com.project.it_job.request.auth.RegisterRequest;
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
    private final RegisterMapper registerMapper;
    private final RoleRepository roleRepository;

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

        AccessToken entity = new AccessToken();
        entity.setToken(accessToken);
        entity.setUser(user);
        entity.setIsRevoked(false);
        entity.setCreateDate(LocalDateTime.now());
        accessTokenRepository.save(entity);
        return new TokenDTO(accessToken, refreshToken);
    }

    @Override
    public RegisterDTO register(RegisterRequest registerRequest) {
        userRepository.existsByEmail(registerRequest.getEmail())
                .orElseThrow(() -> new ConflictException("Email đã tồn tại!!"));

        Role defaultRole = roleRepository.findByRoleNameIgnoreCase("USER")
                .orElseGet(() -> roleRepository.save(Role.builder().roleName("USER").build()));

        User user = registerMapper.saveRegister(registerRequest, defaultRole);

        return registerMapper.toRegisterDTO(userRepository.save(user));
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