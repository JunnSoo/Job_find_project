package com.project.codinviec.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class JobLevelRequest {
    @NotBlank(message = "Tên cấp bậc công việc không được để trống")
    private String name;
}
