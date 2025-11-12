package com.project.it_job.controller.auth;

import com.project.it_job.dto.auth.TokenDTO;
import com.project.it_job.request.auth.AuthRequest;
import com.project.it_job.request.auth.LoginRequest;
import com.project.it_job.exception.RefreshTokenExceptionHanlder;
import com.project.it_job.request.auth.LoginRequest;
import com.project.it_job.request.auth.RegisterRequest;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.auth.AuthService;
import com.project.it_job.util.JWTTokenUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import jakarta.servlet.http.Cookie;
import com.project.it_job.util.CookieHelper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthService authService;
    private final CookieHelper cookieHelper;
    @GetMapping
    public String getAuthentication(){
//        ==> tạo key cho jwt khi nào xong logic authentication thì hãy xóa
//        SecretKey key = Jwts.SIG.HS256.key().build();
//        String secretString = Encoders.BASE64.encode(key.getEncoded());
//        return secretString;

        return "";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request, HttpServletResponse response) {
        TokenDTO tokenDTO = authService.login(request);
        cookieHelper.addRefreshTokenCookie(response, tokenDTO.getRefreshToken());
        return ResponseEntity.ok(BaseResponse.success(tokenDTO, "OK"));

    }

    @PostMapping("/regsiter")
    public ResponseEntity<?> regsiter(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(BaseResponse.success(authService.register(registerRequest), "OK"));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@CookieValue(name = "refresh_token", required = false) String refreshToken, HttpServletResponse response) {
        if (refreshToken == null || refreshToken.isEmpty()) {
            throw new RefreshTokenExceptionHanlder("Không tìm thấy Refresh Token!");
        }

        try {
            TokenDTO tokenDTO = authService.refreshToken(refreshToken);
            cookieHelper.addRefreshTokenCookie(response, tokenDTO.getRefreshToken());
            return ResponseEntity.ok(BaseResponse.success(tokenDTO, "OK"));
        } catch (RefreshTokenExceptionHanlder e) {
            cookieHelper.clearRefreshTokenCookie(response);
            throw e;
        }
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestParam String id, HttpServletResponse response) {
        authService.logout(id);
        cookieHelper.clearRefreshTokenCookie(response);
        return ResponseEntity.ok(BaseResponse.success(null,"OK"));
    }
}
