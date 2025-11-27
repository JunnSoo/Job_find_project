package com.project.codinviec.service.auth;

public interface TokenManagerService {
    void revokeAllTokens(String userId);
}
