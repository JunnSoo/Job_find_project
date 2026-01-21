package com.project.codinviec.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.codinviec.dto.auth.JwtUserDTO;
import com.project.codinviec.entity.auth.User;
import com.project.codinviec.exception.auth.AccessTokenExceptionHandler;
import com.project.codinviec.exception.common.NotFoundIdExceptionHandler;
import com.project.codinviec.repository.auth.UserRepository;
import com.project.codinviec.response.BaseResponse;
import com.project.codinviec.service.auth.TokenManagerService;
import com.project.codinviec.util.security.CookieHelper;
import com.project.codinviec.util.security.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
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
    private final TokenManagerService tokenManagerService;
    private final UserRepository userRepository;
    private final CookieHelper cookieHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        String path = request.getServletPath();



        if (token != null && token.startsWith("Bearer ") && !path.equals("/auth/refresh")) {
            token = token.substring(7);

            try {
                JwtUserDTO jwtUser = tokenManagerService.verifyAccessToken(token);
                // load user từ DB
                User user = userRepository.findById(jwtUser.getUserId())
                        .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy user!"));
//                lỗi 410 bị block nhé
                if (user.getIsBlock()) {
//                    Xóa cookies refresh
                    cookieHelper.clearRefreshTokenCookie(response);
//                    Xóa redis
                    tokenManagerService.revokeAllTokens(user.getId());
                    response.setStatus(410);
                    response.setContentType("application/json;charset=UTF-8");
                    BaseResponse baseResponse = BaseResponse.error("Tài khoản đã bị khoá vĩnh viễn bởi admin", HttpStatus.valueOf(response.getStatus()));
                    new ObjectMapper().writeValue(response.getOutputStream(), baseResponse);
                    return;
                }

                List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + jwtUser.getRole());
                grantedAuthorities.add(grantedAuthority);


                CustomUserDetails customUserDetails = CustomUserDetails.builder()
                        .userId(user.getId())
                        .blocked(user.getIsBlock())
                        .authorities(grantedAuthorities)
                        .build();


//                thẻ thông hành
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(customUserDetails, "", customUserDetails.getAuthorities());

                SecurityContext securityContext = SecurityContextHolder.getContext();
                securityContext.setAuthentication(authentication);
            } catch (AccessTokenExceptionHandler e) {
                handleJwtException(response ,e.getMessage());
            }
        }
        filterChain.doFilter(request,response);

    }

    //    Tạo ra hàm này vì JJWT nó bắt lỗi ở filter luôn mà không vô được @RestControllerAdvise
//    Nên phải custom ở ngoài này
    private void handleJwtException(HttpServletResponse response,String message) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json; charset=UTF-8");
        BaseResponse baseResponse = BaseResponse.error(message, HttpStatus.UNAUTHORIZED);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), baseResponse);
    }
}