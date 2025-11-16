package com.project.it_job.request.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
