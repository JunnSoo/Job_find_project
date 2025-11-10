package com.project.it_job.request.auth;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}