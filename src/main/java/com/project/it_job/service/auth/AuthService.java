package com.project.it_job.service.auth;

import com.project.it_job.dto.auth.TokenDTO;

public interface AuthService {
    TokenDTO login(String email, String password);
    void logout(String email);
}
