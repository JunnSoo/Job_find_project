package com.project.codinviec.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequest {
    @NotBlank(message = "User ID không được để trống")
    private String userId;

    @NotBlank(message = "Tên dự án không được để trống")
    private String name;

    @NotNull(message = "Ngày bắt đầu không được null")
    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String projectUrl;

    @NotBlank(message = "Tên công ty không được để trống")
    private String company;
}
