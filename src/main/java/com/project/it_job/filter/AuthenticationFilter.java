package com.project.it_job.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.it_job.dto.auth.TokenDTO;
import com.project.it_job.exception.AccessTokenExceptionHandler;
import com.project.it_job.exception.RefreshTokenExceptionHanlder;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.auth.AuthService;
import com.project.it_job.util.CookieHelper;
import com.project.it_job.util.JWTHelpper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {
    private final JWTHelpper jwtHelpper;
    private final AuthService authService;
    private final CookieHelper cookieHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        String path = request.getServletPath();

        if (token != null && token.startsWith("Bearer ") && !path.equals("/api/auth/refresh")) {
            token = token.substring(7);

            String role = "";
            try {
                role = jwtHelpper.verifyAccessToken(token);

                // Chỉ set authentication nếu role không rỗng
                if (role != null && !role.isEmpty()) {
                    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + role);
                    grantedAuthorities.add(grantedAuthority);

                    // Thẻ thông hành
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken("", "",
                            grantedAuthorities);

                    SecurityContext securityContext = SecurityContextHolder.getContext();
                    securityContext.setAuthentication(authentication);
                }
            } catch (ExpiredJwtException e) {
                // ExpiredJwtException token hết hạn, tự động refresh
                if (tryAutoRefreshToken(request, response)) {
                    // Refresh thành công, tiếp tục request
                    filterChain.doFilter(request, response);
                } else {
                    // Refresh thất bại, trả về lỗi
                    cookieHelper.clearRefreshTokenCookie(response);
                    handleJwtException(response, "Token đã hết hạn, vui lòng đăng nhập lại");
                }
                return;
            } catch (AccessTokenExceptionHandler e) {
                // Các lỗi khác (revoked, không tìm thấy, không hợp lệ) → không refresh
                handleJwtException(response, e.getMessage());
                return;
            }
        }
        filterChain.doFilter(request, response);

    }

    // Thử tự động refresh token khi AccessToken hết hạn

    private boolean tryAutoRefreshToken(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Lấy RefreshToken từ cookie
            String refreshToken = getRefreshTokenFromCookie(request);

            if (refreshToken == null || refreshToken.isEmpty()) {
                return false;
            }

            // Gọi service để refresh token
            TokenDTO newTokens = authService.refreshToken(refreshToken);
            cookieHelper.addRefreshTokenCookie(response, newTokens.getRefreshToken());

            // Set AccessToken mới vào response header để frontend có thể đọc
            response.setHeader("X-New-Access-Token", newTokens.getAccessToken());

            // Verify AccessToken mới để lấy role
            String role = jwtHelpper.verifyAccessToken(newTokens.getAccessToken());

            // Set authentication với AccessToken mới
            if (role != null && !role.isEmpty()) {
                List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + role);
                grantedAuthorities.add(grantedAuthority);

                // Thẻ thông hành
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken("", "",
                        grantedAuthorities);

                SecurityContext securityContext = SecurityContextHolder.getContext();
                securityContext.setAuthentication(authentication);
                return true;
            }

            return false;
        } catch (RefreshTokenExceptionHanlder | AccessTokenExceptionHandler e) {
            // RefreshToken cũng hết hạn hoặc không hợp lệ
            cookieHelper.clearRefreshTokenCookie(response);
            return false;
        } catch (Exception e) {
            // Lỗi khác
            return false;
        }
    }

    // Lấy RefreshToken từ cookie
    private String getRefreshTokenFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("refresh_token".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    // Tạo ra hàm này vì JJWT nó bắt lỗi ở filter luôn mà không vô được @RestControllerAdvise nên phải custom ở ngoài này
    private void handleJwtException(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json; charset=UTF-8");
        BaseResponse baseResponse = BaseResponse.error(message, HttpStatus.UNAUTHORIZED);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), baseResponse);
    }
}
