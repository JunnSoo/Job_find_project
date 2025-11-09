package com.project.it_job.controller.auth;

import com.project.it_job.dto.auth.TokenDTO;
import com.project.it_job.request.auth.AuthRequest;
import com.project.it_job.service.auth.AuthService;
import com.project.it_job.util.JWTHelpper;
//import com.project.it_job.util.JWTTokenUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthService authService;
    private final JWTHelpper jwtHelpper;
    @GetMapping
    public String getAuthentication(){
//        ==> tạo key cho jwt khi nào xong logic authentication thì hãy xóa
//        SecretKey key = Jwts.SIG.HS256.key().build();
//        String secretString = Encoders.BASE64.encode(key.getEncoded());
//        return secretString;

        return "";
    }
    @PostMapping("/login")
    public TokenDTO login(@Valid @RequestBody AuthRequest request, HttpServletResponse response) {
        TokenDTO token = authService.login(request.getEmail(), request.getPassword());
        Cookie cookie = new Cookie("user_email", request.getEmail());
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookie);

        return token;
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Thiếu token trong header!");
        }

        String token = authHeader.substring(7); // Cắt bỏ 'Bearer '
        String email = jwtHelpper.verifyAccessToken(token); // lấy email từ token

        authService.logout(email);
        return ResponseEntity.ok("Đăng xuất thành công!");
    }
}
