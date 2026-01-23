package com.project.codinviec.constant;

/**
 * Constants cho Security Configuration
 * Chứa các URL patterns cho public và private endpoints
 */
public final class SecurityConstants {

    private SecurityConstants() {
        // Utility class - không cho phép khởi tạo
    }

    // ==================== PUBLIC URLs (Không cần authentication)
    // ====================

    public static final String[] API_PUBLIC_URLS = {
            "/api/auth/login",
            "/api/auth/login-google",
            "/api/auth/register",
            "/api/auth/refresh",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/file/**",
            "/api/payment/**",
            "/api/status-special/**",
            "/api/company-address/**",
            "/images/**",
            "/favicon.ico",
            "/api/group-core-skill/**",
            "/api/auth/profile/**",
            "/api/auth/logout",
            "/api/available-skill-experience/**",

    };

    public static final String[] API_PUBLIC_GET_URLS = {
            "/api/job/**",
            "/api/province/**",
            "/api/ward/**",
            "/api/blog/**",
            "/api/category/**",
            "/api/company/**",
            "/api/review/**",
            "/api/search/**",
            "/api/employment-type/**",
            "/api/joblevel/**",
            "/api/job-level/**",
            "/api/degree-level/**",
            "/api/industry/**",
            "/api/report-status/**",
            "/api/company-size/**",
            "/api/language/**",
            "/api/level-language/**",
            "/api/experience/**",
            "/api/group-core-skill/**",
            "/api/available-skill/**",
            "/api/user/company/**"
    };

    public static final String[] RESOURCE_URLS = {
            "/css/**",
            "/images/**",
            "/js/**"
    };

    // ==================== PRIVATE URLs (Cần authentication)
    // ====================

    public static final String[] ADMIN_URLS = {
            "/api/roles/**",
            "/api/user/**",
            "/api/blog/**"
    };

    public static final String[] USER_URLS = {
            "/api/cv-users/**",
            "/api/wishlist-job/**",
            "/api/wishlist-candidate/**",
            "/api/certificate/**",
            "/api/award/**",
            "/api/project/**",
            "/api/language-skill/**",
            "/api/soft-skills-name/**",
            "/api/reports/**",
            "/api/payment/**",
            "/api/payment-method/**",
            "/api/service-product/**",
            "/api/auth/change-find-job/**"
    };

    public static final String[] USER_WRITE_URLS = {
            "/api/job/**",
            "/api/category/**",
            "/api/review/**",
            "/api/company/**"

    };

    public static final String[] ADMIN_WRITE_URLS = {
            "/api/province/**",
            "/api/ward/**",
            "/api/employment-type/**",
            "/api/joblevel/**",
            "/api/job-level/**",
            "/api/degree-level/**",
            "/api/industry/**",
            "/api/report-status/**",
            "/api/company-size/**",
            "/api/language/**",
            "/api/level-language/**",
            "/api/experience/**",
            "/api/available-skill/**"
    };
}
