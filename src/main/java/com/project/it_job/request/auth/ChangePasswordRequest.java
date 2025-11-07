package com.project.it_job.request.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordRequest {
    private String oldPassword;
    private String newPassword;
}
