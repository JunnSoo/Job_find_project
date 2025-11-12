package com.project.it_job.service.auth;

import com.project.it_job.dto.auth.RegisterDTO;
import com.project.it_job.dto.auth.TokenDTO;
import com.project.it_job.request.auth.LoginRequest;
import com.project.it_job.request.auth.RegisterRequest;


public interface AuthService {
    TokenDTO login(LoginRequest request);
    TokenDTO refreshToken(String refreshToken);
    RegisterDTO register(RegisterRequest registerRequest);
    void logout(String email);
}
