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

    @NotBlank(message = "Họ không được để trống")
    @Size(min = 1, max = 50, message = "Họ phải từ 1 đến 50 ký tự")
    private String firstName;

    @NotBlank(message = "Tên không được để trống")
    @Size(min = 1, max = 50, message = "Tên phải từ 1 đến 50 ký tự")
    private String lastName;

    @NotNull(message = "phone không được null")
    private String phone;

    @NotNull(message = "gender không được null")
    private String gender;

    @NotNull(message = "education không được null")
    private String education;

    @NotNull(message = "address không được null")
    private String address;

    @NotNull(message = "websiteLink không được null")
    private String websiteLink;

    @NotNull(message = "birthDate không được null")
    private LocalDateTime birthDate;

    private String companyId;

    @NotBlank(message = "Role ID không được để trống")
    private String roleId;
}
