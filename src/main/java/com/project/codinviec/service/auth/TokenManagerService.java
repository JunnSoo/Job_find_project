package com.project.codinviec.service.auth;

import com.project.codinviec.dto.auth.AccessTokenDTO;
import com.project.codinviec.dto.auth.JwtUserDTO;
import com.project.codinviec.dto.auth.RefreshTokenDTO;

public interface TokenManagerService {
    void revokeAllTokens(String userId);

    String createAccessToken(String roles, String userId);

    String createRefreshToken(String roles, String userId);

    JwtUserDTO verifyAccessToken(String accessToken);

    JwtUserDTO verifyRefreshToken(String refreshToken);

    AccessTokenDTO  getAccessToken(String userId);
    RefreshTokenDTO getRefreshToken(String userId);


}
