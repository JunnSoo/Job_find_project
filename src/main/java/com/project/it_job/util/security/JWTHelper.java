package com.project.it_job.util.security;

import com.project.it_job.dto.auth.JwtUserDTO;
import com.project.it_job.entity.auth.AccessToken;
import com.project.it_job.entity.auth.RefreshToken;
import com.project.it_job.entity.auth.User;
import com.project.it_job.exception.auth.AccessTokenExceptionHandler;
import com.project.it_job.exception.auth.ExpireTokenExceptionHandler;
import com.project.it_job.exception.common.NotFoundIdExceptionHandler;
import com.project.it_job.exception.auth.RefreshTokenExceptionHandler;
import com.project.it_job.repository.auth.AccessTokenRepository;
import com.project.it_job.repository.auth.RefreshTokenRepository;
import com.project.it_job.repository.auth.UserRepository;
import com.project.it_job.service.auth.TokenManagerService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JWTHelper {
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration.access}")
    private long expirationTime;

    @Value("${jwt.expiration.refresh}")
    private long expirationRefresh;

    private final AccessTokenRepository accessTokenRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    private final TokenManagerService tokenManagerService;

    private final UserRepository userRepository;


    public String createAccessToken(String roles, String userId) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        // Kiểm tra users này có những access token nào
        List<AccessToken> accessTokenDB = accessTokenRepository.findByUser_Id(userId);

        // Trường hợp có token trên db
        if (!accessTokenDB.isEmpty()) {
            for (AccessToken accessToken : accessTokenDB) {
                if (!accessToken.getIsRevoked()) {
                    accessToken.setIsRevoked(true);
                    accessTokenRepository.save(accessToken);
                }
            }
        }
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);
        // accessToken trả ra
        String accessTokenResult = Jwts.builder()
                .subject(roles) // lưu roles
                .issuer(userId) // lưu id user
                .expiration(expirationDate)
                .claim("type", "access_token")
                .signWith(key)
                .compact();

        // Tạo lưu lên db
        AccessToken accessToken = AccessToken.builder()
                .user(User.builder()
                        .id(userId)
                        .build())
                .token(accessTokenResult)
                .expiryDate(LocalDateTime.now().plus(Duration.ofMillis(expirationTime)))
                .createdDate(LocalDateTime.now())
                .isRevoked(false)
                .build();
        accessTokenRepository.save(accessToken);
        return accessTokenResult;
    }

    public String createRefershToken(String roles, String userId) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));

        List<RefreshToken> refreshTokenList = refreshTokenRepository.findByUser_Id((userId));
        if (!refreshTokenList.isEmpty()) {
            for (RefreshToken refreshToken : refreshTokenList) {
                if (!refreshToken.getIsRevoked()) {
                    refreshToken.setIsRevoked(true);
                    refreshTokenRepository.save(refreshToken);
                }
            }
        }

        Date expirationDate = new Date(System.currentTimeMillis() + expirationRefresh);

        String refreshToken = Jwts.builder()
                .subject(roles)
                .issuer(userId)
                .expiration(expirationDate)
                .claim("type", "refresh_token")
                .signWith(key)
                .compact();

        RefreshToken resultToken = RefreshToken.builder()
                .user(User.builder()
                        .id(userId)
                        .build())
                .token(refreshToken)
                .expiryDate(LocalDateTime.now().plus(Duration.ofMillis(expirationRefresh)))
                .createdDate(LocalDateTime.now())
                .isRevoked(false)
                .build();

        refreshTokenRepository.save(resultToken);
        return refreshToken;
    }

    public String verifyRefreshToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));

        try {
            Jws<Claims> tokenValidate = Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseSignedClaims(token);

            Claims claims = tokenValidate.getBody();
            String userId = claims.getIssuer();

            RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                    .orElseThrow(() -> new RefreshTokenExceptionHandler("Không tìm thấy token"));

            if (refreshToken.getIsRevoked()) {
                throw new RefreshTokenExceptionHandler("Refresh Token đã bị thu hồi!");
            }

            if (refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {
                throw new ExpireTokenExceptionHandler("Refresh token đã hết hạn");
            }

            if (claims.get("type").equals("refresh_token")
                    && !claims.getSubject().isEmpty()
                    && !claims.getIssuer().isEmpty()) {

                userRepository.findById(userId)
                        .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy UserId"));

                if (!refreshToken.getUser().getId().equals(userId)) {
                    throw new RefreshTokenExceptionHandler("User token không trùng khớp");
                }
            }
            return userId;
        } catch (ExpiredJwtException e) {
            String userId = e.getClaims().getIssuer();
            removeAllToken(userId);
            throw new ExpireTokenExceptionHandler("Hết hạn Token, Vui lòng đăng nhập lại");
        } catch (Exception e) {
            throw new RefreshTokenExceptionHandler("Token không hợp lệ!");
        }
    }

    public JwtUserDTO verifyAccessToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        try {
            Jws<Claims> tokenValidate = Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseSignedClaims(token);

            Claims claims = tokenValidate.getBody();

            if (!"access_token".equals(claims.get("type"))
                    || claims.getSubject() == null
                    || claims.getIssuer() == null) {
                throw new AccessTokenExceptionHandler("Token không phải access token!");
            }

            String userId = claims.getIssuer();
            String role = claims.getSubject();

            // token phải có trên database và chưa bị thu hồi
            AccessToken accessToken = accessTokenRepository.findByToken(token)
                    .orElseThrow(() -> new AccessTokenExceptionHandler("Không tìm thấy token trong database!"));

            if (accessToken.getIsRevoked()) {
                throw new AccessTokenExceptionHandler("Access Token đã bị thu hồi!");
            }

            if (accessToken.getExpiryDate().isBefore(LocalDateTime.now())) {
                throw new AccessTokenExceptionHandler("Token đã hết hạn");

            // token phải có trên database và chưa bị thu hồi
            AccessToken accessToken = accessTokenRepository.findByToken(token)
                    .orElseThrow(() -> new AccessTokenExceptionHandler("Không tìm thấy token trong database!"));

            if (accessToken.getIsRevoked()) {
                throw new AccessTokenExceptionHandler("Access Token đã bị thu hồi!");
            }

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new NotFoundIdExceptionHandler("User không tìm thấy userID"));

            if (!accessToken.getUser().getId().equals(user.getId())) {
                throw new AccessTokenExceptionHandler("User token không trùng khớp");
            }

            return JwtUserDTO.builder()
                    .userId(userId)
                    .role(role)
                    .build();

        } catch (ExpiredJwtException e) {
            throw new AccessTokenExceptionHandler("Token đã hết hạn!!");
        } catch (JwtException e) {
            throw new AccessTokenExceptionHandler("Token truyền vào không được hỗ trợ");
        }
    }


    public void removeAllToken(String userId) {
        tokenManagerService.revokeAllTokens(userId);
    }

}
