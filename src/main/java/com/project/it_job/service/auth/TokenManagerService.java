package com.project.it_job.service.auth;

public interface TokenManagerService {
    void revokeAllTokens(String userId);
}
