package com.project.codinviec.service.imp.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.codinviec.dto.auth.AccessTokenDTO;
import com.project.codinviec.dto.auth.JwtUserDTO;
import com.project.codinviec.dto.auth.RefreshTokenDTO;
import com.project.codinviec.entity.auth.User;
import com.project.codinviec.exception.auth.AccessTokenExceptionHandler;
import com.project.codinviec.exception.auth.RefreshTokenExceptionHandler;
import com.project.codinviec.repository.auth.UserRepository;
import com.project.codinviec.service.auth.TokenManagerService;
import com.project.codinviec.util.helper.TimeHelper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class TokenManagerServiceImp implements TokenManagerService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration.access}")
    private long expirationTime;

    @Value("${jwt.expiration.refresh}")
    private long expirationRefresh;

    private final TimeHelper timeHelper;

    @Autowired
    @Qualifier("redisTemplateDb1")
    private final RedisTemplate<String, String> redisTemplateDb1;
    private final ObjectMapper objectMapper;

    private final String PREFIX_ACCESTOKEN = "_ACCESSTOKEN";
    private final String PREFIX_REFRESHTOKEN = "_REFRESHTOKEN";

    private final UserRepository userRepository;

    private SecretKey keyParse;

    @PostConstruct
    public void init() {
        this.keyParse = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }


    @Override
    public void revokeAllTokens(String userId) {
        redisTemplateDb1.delete(userId + PREFIX_ACCESTOKEN);
        redisTemplateDb1.delete( userId + PREFIX_REFRESHTOKEN);
    }

    @Override
    public String createAccessToken(String roles, String userId) {
        try{
            Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);
            String key = userId + PREFIX_ACCESTOKEN;

            String accessTokenReuslt = Jwts.builder()
                    .subject(roles)
                    .issuer(userId)
                    .expiration(expirationDate)
                    .claim("type", "access_token")
                    .signWith(keyParse)
                    .compact();

            AccessTokenDTO accessTokenDTO = AccessTokenDTO.builder()
                    .userId(userId)
                    .token(accessTokenReuslt)
                    .expiryDate(timeHelper.nowPlusToString(expirationTime))
                    .createdDate(timeHelper.parseLocalDateTimeToSimpleTime(LocalDateTime.now()))
                    .build();

            String json = objectMapper.writeValueAsString(accessTokenDTO);

            redisTemplateDb1.opsForValue().set(key, json, Duration.ofMinutes(15));

            return accessTokenReuslt;
        }catch (Exception e) {
            throw new AccessTokenExceptionHandler("Không thể tạo AccessToken!");
        }
    }

    @Override
    public String createRefreshToken(String roles, String userId) {
        try{
            Date expirationDate = new Date(System.currentTimeMillis() + expirationRefresh);
            String key = userId + PREFIX_REFRESHTOKEN;

            String refreshTokenResult = Jwts.builder()
                    .subject(roles)
                    .issuer(userId)
                    .expiration(expirationDate)
                    .claim("type", "refresh_token")
                    .signWith(keyParse)
                    .compact();


            RefreshTokenDTO refreshTokenDTO = RefreshTokenDTO.builder()
                    .userId(userId)
                    .token(refreshTokenResult)
                    .expiryDate(timeHelper.nowPlusToString(expirationRefresh))
                    .createdDate(timeHelper.parseLocalDateTimeToSimpleTime(LocalDateTime.now()))
                    .build();


            String json = objectMapper.writeValueAsString(refreshTokenDTO);

            redisTemplateDb1.opsForValue().set(key, json, Duration.ofDays(7));
            return refreshTokenResult;
        }catch (Exception e) {
            throw new RefreshTokenExceptionHandler("Không thể tạo RefreshToken");
        }
    }

    @Override
    public JwtUserDTO verifyAccessToken(String accessToken) {
        try{
            Jws<Claims> tokenValidate = Jwts.parser()
                    .verifyWith(keyParse)
                    .build()
                    .parseClaimsJws(accessToken);

            Claims claims = tokenValidate.getBody();

            if(!claims.get("type").equals("access_token")
                    && claims.getSubject().isEmpty()
                    && claims.getIssuer().isEmpty()){
                throw new AccessTokenExceptionHandler("Token không hợp lệ!");
            }

            String userId = claims.getIssuer();
            String role = claims.getSubject();

            AccessTokenDTO accessTokenDTO = getAccessToken(userId);

            if(accessTokenDTO == null){
                throw new AccessTokenExceptionHandler("Token hết hạn!");
            }

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new AccessTokenExceptionHandler("Không tìm thấy user!"));

            if(!accessTokenDTO.getUserId().equals(user.getId())){
                throw new AccessTokenExceptionHandler("User không trùng khớp token!");
            }

            return JwtUserDTO.builder()
                    .userId(userId)
                    .role(role)
                    .build();
        }catch (ExpiredJwtException e){
            throw new AccessTokenExceptionHandler("Token đã hết hạn!!");
        }
        catch (JwtException e) {
            throw new AccessTokenExceptionHandler("Token truyền vào không được hỗ trợ");
        }
    }

    @Override
    public JwtUserDTO verifyRefreshToken(String refreshToken) {
        try{
            Jws<Claims> tokenValidate = Jwts.parser()
                    .verifyWith(keyParse)
                    .build()
                    .parseSignedClaims(refreshToken);

            Claims claims = tokenValidate.getBody();

            String userId = claims.getIssuer();
            String role = claims.getSubject();

            RefreshTokenDTO refreshTokenDTO = getRefreshToken(userId);

            if(refreshTokenDTO == null){
                throw new RefreshTokenExceptionHandler("Token hết hạn!");
            }

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RefreshTokenExceptionHandler("Không tìm thấy user!"));

            if(!refreshTokenDTO.getUserId().equals(user.getId())){
                throw new RefreshTokenExceptionHandler("User không trùng khớp token!");
            }

            return JwtUserDTO.builder()
                    .userId(userId)
                    .role(role)
                    .build();
        }
        catch (ExpiredJwtException e){
            String userId = e.getClaims().get("userId").toString();
            revokeAllTokens(userId);
            throw new RefreshTokenExceptionHandler("Token đã hết hạn");
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AccessTokenDTO getAccessToken(String userId) {
        try{
            String key = userId + PREFIX_ACCESTOKEN;
            if(redisTemplateDb1.hasKey(key)){
                String json = redisTemplateDb1.opsForValue().get(key);
                return objectMapper.readValue(json, AccessTokenDTO.class);
            }
            return null;
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public RefreshTokenDTO getRefreshToken(String userId) {
        try{
            String key = userId + PREFIX_REFRESHTOKEN;
            if(redisTemplateDb1.hasKey(key)){
                String json = redisTemplateDb1.opsForValue().get(key);
                return objectMapper.readValue(json, RefreshTokenDTO.class);
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }
}
