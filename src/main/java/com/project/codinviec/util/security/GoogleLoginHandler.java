package com.project.codinviec.util.security;

import com.project.codinviec.dto.auth.TokenDTO;
import com.project.codinviec.exception.auth.AlreadyLoggedInExceptionHandler;
import com.project.codinviec.request.auth.GoogleUserRequest;
import com.project.codinviec.service.auth.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class GoogleLoginHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Value("${client.url}")
    private String clientUrl;

    private final AuthService authService;
    private final CookieHelper cookieHelper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        // Lấy thông tin từ Google
        String email = (String) attributes.get("email");
        String firstName = (String) attributes.get("given_name");
        String lastName = (String) attributes.get("family_name");
        String picture = (String) attributes.get("picture");

        System.out.println("----------------------------------------------------------" + clientUrl);
        log.info("Google login successful - Email: {}, Name: {} {}", email, firstName, lastName);
        log.debug("Google user attributes: {}", attributes);

        // Validate email (required)
        if (email == null || email.isEmpty()) {
            log.error("Email is missing from Google user attributes");
            String redirectUrl = String.format("%s/login?error=no_email", clientUrl);
            getRedirectStrategy().sendRedirect(request, response, redirectUrl);
            return;
        }

        try {
            // Tạo GoogleUserRequest từ thông tin Google
            GoogleUserRequest googleUserRequest = GoogleUserRequest.builder()
                    .email(email)
                    .firstName(firstName)
                    .lastName(lastName)
                    .picture(picture)
                    .build();

            // Xử lý user từ Google login
            TokenDTO tokenDTO = authService.googleLogin(googleUserRequest);

            // Validate token
            if (tokenDTO == null || tokenDTO.getAccessToken() == null || tokenDTO.getAccessToken().isEmpty()) {
                log.error("TokenDTO is null or access token is empty");
                String redirectUrl = String.format("%s/login?error=no_token", clientUrl);
                getRedirectStrategy().sendRedirect(request, response, redirectUrl);
                return;
            }

            // Thêm refresh token vào cookie
            cookieHelper.addRefreshTokenCookie(response, tokenDTO.getRefreshToken());

            // URL encode token để tránh lỗi với các ký tự đặc biệt
            String encodedToken = URLEncoder.encode(tokenDTO.getAccessToken(), StandardCharsets.UTF_8);

            // Redirect về home page (root) với access token trong query parameter
            String redirectUrl = String.format("%s/login?token=%s", clientUrl, encodedToken);

            log.info("Redirecting to home page with token");
            getRedirectStrategy().sendRedirect(request, response, redirectUrl);

        } catch (AlreadyLoggedInExceptionHandler e) {
            // Tài khoản đã đăng nhập ở nơi khác
            log.warn("AlreadyLoggedInException: {}", e.getMessage());
            String redirectUrl = String.format("%s/login?error=logged_in", clientUrl);
            getRedirectStrategy().sendRedirect(request, response, redirectUrl);
        } catch (Exception e) {
            log.error("Error processing Google login: {}", e.getMessage(), e);
            // Xác định error code dựa trên exception
            String errorCode = "error";
            if (e.getMessage() != null) {
                String lowerMessage = e.getMessage().toLowerCase();
                if (lowerMessage.contains("email")) {
                    errorCode = "email_err";
                } else if (lowerMessage.contains("token")) {
                    errorCode = "token_err";
                }
            }
            String redirectUrl = String.format("%s/login?error=%s", clientUrl, errorCode);
            getRedirectStrategy().sendRedirect(request, response, redirectUrl);
        }
    }
}
