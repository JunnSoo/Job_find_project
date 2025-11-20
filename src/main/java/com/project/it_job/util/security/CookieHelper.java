package com.project.it_job.util.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class CookieHelper {

    @Value("${cookie.secure:false}")
    private boolean cookieSecure;

    /**
     * Thêm refresh token vào cookie
     * HttpOnly: true - JavaScript không thể truy cập (bảo mật)
     * Secure: chỉ true trong production (HTTPS), false trong development (HTTP)
     * SameSite: Strict - chống CSRF
     */
    public void addRefreshTokenCookie(HttpServletResponse response, String refreshToken) {
        ResponseCookie cookie = ResponseCookie.from("refresh_token", refreshToken)
                .httpOnly(true) // JavaScript không thể truy cập - BẢO MẬT
                .secure(cookieSecure) // Chỉ dùng HTTPS trong production
                .path("/")
                .sameSite("Strict")
                .maxAge(Duration.ofDays(7))
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    /**
     * Xóa refresh token cookie
     */
    public void clearRefreshTokenCookie(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from("refresh_token", "")
                .httpOnly(true)
                .secure(cookieSecure)
                .path("/")
                .maxAge(0)
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }
}
