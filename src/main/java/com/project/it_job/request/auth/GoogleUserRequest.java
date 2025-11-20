package com.project.it_job.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request DTO cho OAuth2 User từ Google
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoogleUserRequest {

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    private String firstName;

    private String lastName;

    private String picture;
}
