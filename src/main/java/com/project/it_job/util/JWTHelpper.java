package com.project.it_job.util;

import com.project.it_job.entity.auth.AccessToken;
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

@Component
@RequiredArgsConstructor
public class JWTHelpper {
    @Value("${jwt.secret}")
    private String secretKey;
    private final AccessTokenRepository accessTokenRepository;

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
