package com.project.codinviec.controller.auth;

import com.project.codinviec.dto.auth.TokenDTO;
import com.project.codinviec.exception.auth.ExpireTokenExceptionHandler;
import com.project.codinviec.exception.auth.RefreshTokenExceptionHandler;
import com.project.codinviec.request.ChangeSoftSkillRequest;
import com.project.codinviec.request.UpdateAvatarRequest;
import com.project.codinviec.request.auth.LoginRequest;
import com.project.codinviec.request.auth.RegisterRequest;
import com.project.codinviec.request.auth.UpdateProfileRequest;
import com.project.codinviec.response.BaseResponse;
import com.project.codinviec.service.auth.AuthService;
import com.project.codinviec.util.security.CookieHelper;
import com.project.codinviec.util.security.CustomUserDetails;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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
    public ResponseEntity<?> logout(@CookieValue(name = "refresh_token",required = false) String refreshToken, HttpServletResponse response) {
        try {
            authService.logout(refreshToken);
            cookieHelper.clearRefreshTokenCookie(response);
            return ResponseEntity.ok(BaseResponse.success(null, "OK"));
        } catch (RefreshTokenExceptionHandler | ExpireTokenExceptionHandler e) {
            cookieHelper.clearRefreshTokenCookie(response);
            throw new RefreshTokenExceptionHandler("Đăng xuất thất bại!");
        }
    }

    @GetMapping("/login-google")
    public void loginGoogle(HttpServletResponse response) throws IOException {
        response.sendRedirect("/oauth2/authorization/google");
    }

    // Lấy thông tin profile của user hiện tại

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(BaseResponse.success(authService.getProfile(userDetails.getUserId()), "OK"));
    }

    // Cập nhật thông tin profile của user hiện tại
    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody UpdateProfileRequest request) {
        return ResponseEntity
                .ok(BaseResponse.success(authService.updateProfile(userDetails.getUserId(), request), "Cập nhật profile thành công"));
    }

    @PutMapping("/change-find-job")
    public ResponseEntity<?> toggleIsFindJob(
            @AuthenticationPrincipal CustomUserDetails userDetails ) {
        return ResponseEntity
                .ok(BaseResponse.success(authService.toggleIsFindJob(userDetails.getUserId()), "Cập nhật findjob thành công"));
    }

    @PutMapping("/profile/soft-skill")
    public ResponseEntity<?> changeSoftSkillProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails,@RequestBody ChangeSoftSkillRequest changeSoftSkillRequest) {
        return ResponseEntity
                .ok(BaseResponse.success(authService.changeSoftSkill(userDetails.getUserId(), changeSoftSkillRequest), "Cập nhật softSkill thành công"));
    }

    @PutMapping(value = "/profile/avatar",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateAvatar(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @ModelAttribute  UpdateAvatarRequest updateAvatarRequest) {
        return ResponseEntity
                .ok(BaseResponse.success(authService.updateAvatar(userDetails.getUserId(), updateAvatarRequest), "Cập nhật avatar thành công"));
    }
}
