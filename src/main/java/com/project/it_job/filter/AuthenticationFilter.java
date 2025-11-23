package com.project.it_job.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.it_job.dto.auth.TokenDTO;
import com.project.it_job.exception.auth.AccessTokenExceptionHandler;
import com.project.it_job.exception.auth.ExpireTokenExceptionHandler;
import com.project.it_job.exception.auth.RefreshTokenExceptionHandler;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.auth.TokenManagerService;
import com.project.it_job.util.security.CustomUserDetails;
import com.project.it_job.service.auth.AuthService;
import com.project.it_job.util.security.CookieHelper;
import com.project.it_job.util.security.JWTHelper;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {
    private final JWTHelper jwtHelper;
    private final AuthService authService;
    private final CookieHelper cookieHelper;
    private final TokenManagerService tokenManagerService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        String path = request.getServletPath();

        if (token != null && token.startsWith("Bearer ") && !path.equals("/api/auth/refresh")) {
            token = token.substring(7);

            try {

                String role = jwtHelper.verifyAccessToken(token);

                if (role != null && !role.isEmpty()) {
                    setAuthentication(token, role);
                }

            } catch (AccessTokenExceptionHandler e) {
                String errorMessage = e.getMessage();
                boolean isTokenExpired = "ACCESS_TOKEN_EXPIRED".equals(errorMessage);

                if (isTokenExpired) {
                    String newAccessToken = tryAutoRefreshToken(request, response);
                    if (newAccessToken != null && !newAccessToken.isEmpty()) {
                        if (!response.isCommitted()) {
                            response.setHeader("X-New-Access-Token", newAccessToken);
                        }

                        // Authentication đã được set trong SecurityContext, các filter/controller khác
                        filterChain.doFilter(request, response);
                        return;
                    } else {
                        System.out.println("[AuthenticationFilter] Auto refresh thất bại");
                        // Refresh thất bại
                        cookieHelper.clearRefreshTokenCookie(response);
                        // Lấy userId từ token cũ để revoke
                        try {
                            String userId = jwtHelper.getUserIdFromToken(token);
                            if (userId != null) {
                                tokenManagerService.revokeAllTokens(userId);
                            }
                        } catch (Exception ex) {
                            // Không thể lấy userId, bỏ qua
                        }
                        handleJwtException(response, "Phiên đăng nhập hết hạn, vui lòng đăng nhập lại");
                    }
                } else {
                    //không refresh
                    handleJwtException(response, e.getMessage());
                }
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private String tryAutoRefreshToken(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = "";
        try {
            // Lấy từ cookie
            refreshToken = getRefreshTokenFromCookie(request);
            if (refreshToken == null || refreshToken.isEmpty())
                return null;

            // Gọi service refresh
            TokenDTO newTokens = authService.refreshToken(refreshToken);
            // Update refresh token cookie
            cookieHelper.addRefreshTokenCookie(response, newTokens.getRefreshToken());

            // Lấy role từ token mới
            String role = jwtHelper.getRoleFromToken(newTokens.getAccessToken());
            if (role != null && !role.isEmpty()) {
                setAuthentication(newTokens.getAccessToken(), role);
                return newTokens.getAccessToken();
            }

            return null;

        } catch (ExpireTokenExceptionHandler e) {
            cookieHelper.clearRefreshTokenCookie(response);
            if (refreshToken != null) {
                try {
                    String userId = jwtHelper.getUserIdFromToken(refreshToken);
                    if (userId != null) {
                        tokenManagerService.revokeAllTokens(userId);
                    }
                } catch (Exception ignored) {
                }
            }
            return null;
        } catch (RefreshTokenExceptionHandler | AccessTokenExceptionHandler e) {
            // Refresh token invalid hoặc access token lỗi
            cookieHelper.clearRefreshTokenCookie(response);
            return null;
        } catch (Exception e) {
            // Lỗi khác
            cookieHelper.clearRefreshTokenCookie(response);
            return null;
        }
    }

    private void setAuthentication(String token, String role) {
        String userId = jwtHelper.getUserIdFromToken(token);

        List<GrantedAuthority> grantedAuthorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));

        CustomUserDetails userDetails = new CustomUserDetails(userId, grantedAuthorities);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                grantedAuthorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

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

    // Tạo ra hàm này vì JJWT nó bắt lỗi ở filter luôn mà không vô được
    // @RestControllerAdvise nên phải custom ở ngoài này
    private void handleJwtException(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json; charset=UTF-8");
        BaseResponse baseResponse = BaseResponse.error(message, HttpStatus.UNAUTHORIZED);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), baseResponse);
    }
}
