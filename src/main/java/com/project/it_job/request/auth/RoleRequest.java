package com.project.it_job.request.auth;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class RoleRequest {
    @NotEmpty(message = "Role không thể trống!")
    @NotNull(message = "Vui lòng nhập role!")
    private String roleName;

    @NotEmpty(message = "Description không thể trống!")
    @NotNull(message = "Vui lòng nhập description!")
    private String description;

}
