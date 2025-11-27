package com.project.codinviec.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserRequest {
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotBlank(message = "Họ không được để trống")
    @Size(min = 1, max = 50, message = "Họ phải từ 1 đến 50 ký tự")
    private String firstName;

    @NotBlank(message = "Tên không được để trống")
    @Size(min = 1, max = 50, message = "Tên phải từ 1 đến 50 ký tự")
    private String lastName;

    private String avatar;

    private String phone;

    private String gender;

    private String education;

    private String address;

    private String websiteLink;

    //    @NotNull(message = "birthDate must be not null")
    private LocalDateTime birthDate;

    @NotNull(message = "isFindJob không được null")
    private boolean isFindJob;

    private String groupSoftSkill;

    @NotBlank(message = "Company ID không được để trống")
    private String companyId;

    @NotBlank(message = "Role ID không được để trống")
    private String roleId;
}
