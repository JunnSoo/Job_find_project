package com.project.it_job.service.auth;

import com.project.it_job.dto.auth.TokenDTO;
import com.project.it_job.request.auth.LoginRequest;

public interface AuthService {
    TokenDTO login(LoginRequest loginRequest);
    void logout(String email);
}
