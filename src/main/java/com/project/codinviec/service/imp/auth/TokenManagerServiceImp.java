package com.project.codinviec.service.imp.auth;

import com.project.codinviec.repository.auth.AccessTokenRepository;
import com.project.codinviec.repository.auth.RefreshTokenRepository;
import com.project.codinviec.service.auth.TokenManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TokenManagerServiceImp implements TokenManagerService {

    private final AccessTokenRepository accessTokenRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void revokeAllTokens(String userId) {
        accessTokenRepository.revokeAllAccessTokens(userId);
        refreshTokenRepository.revokeAllRefreshTokens(userId);
    }
}
