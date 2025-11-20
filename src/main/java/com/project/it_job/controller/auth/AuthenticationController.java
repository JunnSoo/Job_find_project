package com.project.it_job.controller.auth;

import com.project.it_job.dto.auth.TokenDTO;
import com.project.it_job.exception.auth.RefreshTokenExceptionHandler;
import com.project.it_job.request.auth.LoginRequest;
import com.project.it_job.request.auth.RegisterRequest;
import com.project.it_job.request.auth.UpdateProfileRequest;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.util.security.CustomUserDetails;
import com.project.it_job.service.auth.AuthService;
import com.project.it_job.util.security.CookieHelper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthService authService;
    private final CookieHelper cookieHelper;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request, HttpServletResponse response) {
        TokenDTO tokenDTO = authService.login(request);
        cookieHelper.addRefreshTokenCookie(response, tokenDTO.getRefreshToken());
        return ResponseEntity.ok(BaseResponse.success(tokenDTO, "OK"));

    }

    @PostMapping("/register")
    public ResponseEntity<?> regsiter(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(BaseResponse.success(authService.register(registerRequest), "OK"));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@CookieValue(name = "refresh_token", required = false) String refreshToken,
            HttpServletResponse response) {
        if (refreshToken == null || refreshToken.isEmpty()) {
            throw new RefreshTokenExceptionHandler("Không tìm thấy Refresh Token!");
        }
        try {
            TokenDTO tokenDTO = authService.refreshToken(refreshToken);
            cookieHelper.addRefreshTokenCookie(response, tokenDTO.getRefreshToken());
            return ResponseEntity.ok(BaseResponse.success(tokenDTO, "OK"));
        } catch (RefreshTokenExceptionHandler e) {
            cookieHelper.clearRefreshTokenCookie(response);
            throw e;
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestParam String id, HttpServletResponse response) {
        authService.logout(id);
        cookieHelper.clearRefreshTokenCookie(response);
        return ResponseEntity.ok(BaseResponse.success(null, "OK"));
    }

    @GetMapping("/login-google")
    public void loginGoogle(HttpServletResponse response) throws IOException {
        response.sendRedirect("/oauth2/authorization/google");
    }

    // Lấy thông tin profile của user hiện tại

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(BaseResponse.success(authService.getProfile(userDetails.userId()), "OK"));
    }

    // Cập nhật thông tin profile của user hiện tại
    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody UpdateProfileRequest request) {
        return ResponseEntity
                .ok(BaseResponse.success(authService.updateProfile(userDetails.userId(), request), "Cập nhật profile thành công"));
    }
}
