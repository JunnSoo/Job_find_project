package com.project.codinviec.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmploymentTypeRequest {
    @NotBlank(message = "Tên loại hình công việc không được để trống")
    private String name;
}
