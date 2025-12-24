package com.project.codinviec.mapper.auth;


import com.project.codinviec.dto.auth.RegisterDTO;
import com.project.codinviec.entity.auth.Role;
import com.project.codinviec.entity.auth.User;
import com.project.codinviec.request.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@RequiredArgsConstructor
public class RegisterMapper {
    private final PasswordEncoder passwordEncoder;


    public RegisterDTO toRegisterDTO(User user) {
        return RegisterDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    public User saveRegister(RegisterRequest request, Role role) {
        return User.builder()
                .email(request.getEmail().toLowerCase())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(passwordEncoder.encode(request.getPassword()))
                .isBlock(false)
                .isFindJob(false)
                .role(role)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
    }
}
