package com.project.it_job.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleRequest {
    @NotBlank(message = "Tên vai trò không được để trống")
    private String roleName;

    @NotBlank(message = "Mô tả không được để trống")
    private String description;
}
