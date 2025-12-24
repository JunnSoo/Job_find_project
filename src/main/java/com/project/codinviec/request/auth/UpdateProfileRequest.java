package com.project.codinviec.request.auth;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

/**
 * Request DTO để user cập nhật thông tin profile của chính mình
 * Không bao gồm email, companyId và roleId (chỉ ADMIN mới có quyền thay đổi)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProfileRequest {

    @Size(max = 50, message = "Họ tối đa 50 ký tự")
    @NotNull(message = "firstName không được null")
    @NotEmpty(message = "firstName không được rỗng")
    private String firstName;

    @Size(max = 50, message = "Tên tối đa 50 ký tự")
    @NotNull(message = "lastName không được null")
    @NotEmpty(message = "lastName không được rỗng")
    private String lastName;

    @NotNull(message = "phone không được null")
    @NotEmpty(message = "phone không được rỗng")
    private String phone;

    @NotNull(message = "gender không được null")
    @NotEmpty(message = "gender không được rỗng")
    private String gender;

    @NotNull(message = "birthDate không được null")
    private LocalDateTime birthDate;

    @NotNull(message = "address không được null")
    private String address;

    @NotNull(message = "websiteLink không được null")
    private String websiteLink;

    @NotNull(message = "education không được null")
    private String education;
}
