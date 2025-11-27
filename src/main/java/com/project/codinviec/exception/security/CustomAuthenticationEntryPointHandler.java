package com.project.codinviec.exception.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.codinviec.response.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class CustomAuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException {
        log.warn("AuthenticationException: {} | URI: {} | Method: {} | Message: {}",
                authException.getClass().getSimpleName(),
                request.getRequestURI(),
                request.getMethod(),
                authException.getMessage());

        // Tạo response lỗi
        BaseResponse errorResponse = BaseResponse.error(
                "Bạn chưa đăng nhập. Vui lòng đăng nhập để tiếp tục!",
                HttpStatus.FORBIDDEN);

        // Set response headers
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        // Ghi response body
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        response.getWriter().flush();
    }
}
