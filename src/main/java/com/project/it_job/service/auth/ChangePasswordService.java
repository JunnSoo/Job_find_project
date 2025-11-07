package com.project.it_job.service.auth;

import com.project.it_job.request.auth.ChangePasswordRequest;

public interface ChangePasswordService {
    boolean changePassword(String email, ChangePasswordRequest request);
}
