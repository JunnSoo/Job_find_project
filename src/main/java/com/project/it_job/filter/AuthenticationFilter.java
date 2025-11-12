package com.project.it_job.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.it_job.exception.AccessTokenExceptionHandler;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.util.JWTHelpper;
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
    private final JWTHelpper jwtHelpper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        String path = request.getServletPath();

        if (token != null && token.startsWith("Bearer ") && !path.equals("/auth/refresh")) {
            token = token.substring(7);

            String role = "";
            try {
                role = jwtHelpper.verifyAccessToken(token);
            } catch (AccessTokenExceptionHandler e) {
               handleJwtException(response ,e.getMessage());
            }

            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + role);
            grantedAuthorities.add(grantedAuthority);

//                thẻ thông hành
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken("", "", grantedAuthorities);

                SecurityContext securityContext = SecurityContextHolder.getContext();
                securityContext.setAuthentication(authentication);
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

