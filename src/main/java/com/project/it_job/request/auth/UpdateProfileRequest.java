package com.project.it_job.request.auth;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String firstName;

    @Size(max = 50, message = "Tên tối đa 50 ký tự")
    private String lastName;

    private String avatar;

    private String phone;

    private String gender;

    private String education;

    private String address;

    private String websiteLink;

    private LocalDateTime birthDate;

    private Boolean isFindJob;

    private String groupSoftSkill;
}
