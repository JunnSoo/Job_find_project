package com.project.codinviec.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DegreeLevelRequest {
    @NotBlank(message = "Tên trình độ không được để trống")
    private String name;
}
