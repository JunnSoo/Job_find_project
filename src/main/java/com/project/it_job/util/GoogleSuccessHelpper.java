package com.project.it_job.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.it_job.dto.GoogleInforDTO;
import com.project.it_job.entity.auth.User;
import com.project.it_job.exception.AlreadyLoggedInExceptionHandler;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.GoogleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GoogleSuccessHelpper extends SimpleUrlAuthenticationSuccessHandler {
    private final GoogleService googleService;
    private final JWTHelpper jwtHelppe;
    private final CookieHelper cookieHelper;
    private final JWTHelpper jwtHelpper;



    @Value("${url.client}")
    private String client;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        Map<String, Object> attrs = oauthToken.getPrincipal().getAttributes();

        // Lấy email
        String email = (String) attrs.get("email");

        // Lấy firstname và lastname
        String firstName = (String) attrs.get("given_name");
        String lastName = (String) attrs.get("family_name");

        // Lấy picture
        String picture = (String) attrs.get("picture");

        User user = googleService.getUsersLogin(GoogleInforDTO.builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .picture(picture)
                .build());

//        check đăng nhập ở nơi khác
        try {
            if (!jwtHelpper.jwtCheckingLoginByUserId(user.getId())){
                String accessToken = jwtHelppe.createAccessToken(user.getRole().getRoleName(),user.getId());
                String refreshToken = jwtHelppe.createRefershToken(user.getRole().getRoleName(),user.getId());
                cookieHelper.addRefreshTokenCookie(response,refreshToken);

                // Thành công sẽ chuyển hướng đi -> Redirect về FE kèm token, ví dụ:
                String redirectUrl = client + "/api/auth/google/success?accessToken="
                        + accessToken + "&refreshToken=" + refreshToken;
                getRedirectStrategy().sendRedirect(request, response, redirectUrl);
            }
        } catch (AlreadyLoggedInExceptionHandler ex){
//            trường hợp đăng nhập thất bại hoặc đã đăng nhập ở nơi khác
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            new ObjectMapper().writeValue(response.getWriter(),
                    BaseResponse.error("Tài khoản đăng nhập thất bại!", HttpStatus.BAD_REQUEST));
        }
    }
}
