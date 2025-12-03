package com.project.codinviec.config;

import com.project.codinviec.constant.SecurityConstants;
import com.project.codinviec.exception.security.CustomAccessDeniedHandler;
import com.project.codinviec.exception.security.CustomAuthenticationEntryPointHandler;
import com.project.codinviec.filter.AuthenticationFilter;
import com.project.codinviec.util.security.GoogleLoginHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

        @Value("${client.url}")
        private String localhost;

        private final CustomAccessDeniedHandler customAccessDeniedHandler;
        private final GoogleLoginHandler googleLoginHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationFilter authenticationFilter) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // Tắt CSRF cho REST API (có thể bật lại nếu cần)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Public URLs - Không cần authentication (auth, swagger, file)
                        .requestMatchers(SecurityConstants.API_PUBLIC_URLS).permitAll()

                        // Public GET endpoints - Cho phép xem dữ liệu công khai (chỉ GET
                        // method)
                        .requestMatchers(HttpMethod.GET, SecurityConstants.API_PUBLIC_GET_URLS)
                        .permitAll()

                        // Admin only endpoints - Chỉ ADMIN role (roles, user management)
                        .requestMatchers(SecurityConstants.ADMIN_URLS).hasRole("ADMIN")

                        // Admin write endpoints - POST/PUT/DELETE master data chỉ dành cho
                        // ADMIN
                        .requestMatchers(HttpMethod.POST, SecurityConstants.ADMIN_WRITE_URLS)
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, SecurityConstants.ADMIN_WRITE_URLS)
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, SecurityConstants.ADMIN_WRITE_URLS)
                        .hasRole("ADMIN")

                        // User write endpoints - POST/PUT/DELETE cho user data (bao gồm ADMIN)
                        .requestMatchers(HttpMethod.POST, SecurityConstants.USER_WRITE_URLS)
                        .hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, SecurityConstants.USER_WRITE_URLS)
                        .hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, SecurityConstants.USER_WRITE_URLS)
                        .hasAnyRole("USER", "ADMIN")

                        // User endpoints - Cần authentication (bao gồm ADMIN)
                        .requestMatchers(SecurityConstants.USER_URLS)
                        .hasAnyRole("USER", "ADMIN"))

                // Cấu hình Google Login
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(googleLoginHandler))
                // Add authentication filter trước Spring Security filter
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                // Cấu hình Exception Handling
                .exceptionHandling(ex -> ex
                        // Xử lý khi đã đăng nhập nhưng không có quyền - Trả về 403
                        .accessDeniedHandler(customAccessDeniedHandler))
                .httpBasic(AbstractHttpConfigurer::disable) // Tắt Basic Auth
                .formLogin(AbstractHttpConfigurer::disable); // Tắt form login mặc định

        return http.build();
    }

        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOrigins(List.of(localhost, "http://localhost:3000"));
                configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                configuration.setAllowedHeaders(List.of("*"));
                configuration.setAllowCredentials(true);

                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);

                return source;
        }
}
