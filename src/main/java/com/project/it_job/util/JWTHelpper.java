package com.project.it_job.util;

import com.project.it_job.entity.auth.AccessToken;
import com.project.it_job.entity.auth.User;
import com.project.it_job.exception.AccessTokenExceptionHandler;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.repository.auth.AccessTokenRepository;
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
public class JWTHelpper {
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration.access}")
    private long expirationTime;

    private final AccessTokenRepository accessTokenRepository;

    public String createAccessToken(String roles, String userId){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
//        Kiểm tra users này có những access token nào
        List<AccessToken> accessTokenDB = accessTokenRepository.findByUser_Id(userId);

//        Trường hợp có token trên db
        if(accessTokenDB.size() > 0){
            for(AccessToken accessToken : accessTokenDB){
                if (!accessToken.getIsRevoked()){
                    accessToken.setIsRevoked(true);
                    accessTokenRepository.save(accessToken);
                }
            }
        }
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);
//        accessToken trả ra
        String accessTokenResult = Jwts.builder()
                .subject(roles) // lưu roles
                .issuer(userId) // lưu id user
                .expiration(expirationDate)
                .claim("type", "access_token")
                .signWith(key)
                .compact();

//        Tạo lưu lên db
        AccessToken accessToken = AccessToken.builder()
                .user(User.builder()
                        .id(userId)
                        .build())
                .token(accessTokenResult)
                .expiryDate(LocalDateTime.now().plus(Duration.ofMillis(expirationTime)))
                .createDate(LocalDateTime.now())
                .isRevoked(false)
                .build();
        accessTokenRepository.save(accessToken);
        return accessTokenResult;
    }

    public String verifyAccessToken(String token){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        try {
            Jws<Claims> tokenValidate = Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            Claims claims = tokenValidate.getBody();
            if(claims.get("type").equals("access_token")
                    && !claims.getSubject().isEmpty()
                    && !claims.getIssuer().isEmpty()){

//                    token phải có trên database và chưa bị thu hồi
                AccessToken accessToken = accessTokenRepository.findByToken(token)
                        .orElseThrow(()->  new AccessTokenExceptionHandler("Không tìm thấy token trong database!"));

                if(!accessToken.getIsRevoked()){
                    return claims.getSubject();
                }else {
                    throw new AccessTokenExceptionHandler("Access Token hết hạn!");
                }
            }
        } catch (JwtException  e) {
            e.printStackTrace();
            throw new AccessTokenExceptionHandler("Token truyền vào không được hỗ trợ");
        }
        return null;
    }
}
