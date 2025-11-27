package com.project.codinviec.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class ReportStatusRequest {
    @NotBlank(message = "Tên trạng thái báo cáo không được để trống")
    private String name;
}
