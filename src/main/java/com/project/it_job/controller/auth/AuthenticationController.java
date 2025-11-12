package com.project.it_job.controller.auth;

import com.project.it_job.dto.auth.TokenDTO;
import com.project.it_job.request.auth.AuthRequest;
import com.project.it_job.request.auth.LoginRequest;

import com.project.it_job.request.auth.RegisterRequest;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.auth.AuthService;
import com.project.it_job.util.JWTTokenUtil;
import jakarta.servlet.http.Cookie;
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
    private final JWTTokenUtil jwtTokenUtil;

    @GetMapping
    public String getAuthentication(){
//        ==> tạo key cho jwt khi nào xong logic authentication thì hãy xóa
//        SecretKey key = Jwts.SIG.HS256.key().build();
//        String secretString = Encoders.BASE64.encode(key.getEncoded());
//        return secretString;

        return "";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
      return ResponseEntity.ok(BaseResponse.success(authService.login(loginRequest),"OK"));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Thiếu token trong header!");
        }

        String token = authHeader.substring(7); // Cắt bỏ 'Bearer '
        String email = jwtTokenUtil.extractEmail(token); // lấy email từ token

        authService.logout(email);
        return ResponseEntity.ok("Đăng xuất thành công!");
    }

    @PostMapping
    public ResponseEntity<?> logout() {

        return ResponseEntity.ok(BaseResponse.success(null, "Logout thành công"));

    @PostMapping("/regsiter")
    public ResponseEntity<?> regsiter(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(BaseResponse.success(authService.register(registerRequest), "OK"));
    }
}
