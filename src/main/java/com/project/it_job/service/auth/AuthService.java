package com.project.it_job.service.auth;

import com.project.it_job.dto.auth.RegisterDTO;
import com.project.it_job.dto.auth.TokenDTO;
import com.project.it_job.dto.auth.UserDTO;
import com.project.it_job.request.auth.LoginRequest;
import com.project.it_job.request.auth.GoogleUserRequest;
import com.project.it_job.request.auth.RegisterRequest;
import com.project.it_job.request.auth.UpdateProfileRequest;

public interface AuthService {
    TokenDTO login(LoginRequest request);

    TokenDTO refreshToken(String refreshToken);

    RegisterDTO register(RegisterRequest registerRequest);

    void logout(String email);

    TokenDTO googleLogin(GoogleUserRequest request);

    UserDTO getProfile(String userId);

    UserDTO updateProfile(String userId, UpdateProfileRequest request);
}
