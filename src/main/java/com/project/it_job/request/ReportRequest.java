package com.project.it_job.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReportRequest {
    @NotBlank(message = "Tiêu đề không được để trống")
    private String title;

    @NotBlank(message = "Mô tả không được để trống")
    private String description;

    private String hinhAnh;

    @NotNull(message = "Status ID không được null")
    @Min(value = 1, message = "Status ID phải lớn hơn 0")
    private int statusId;

    @NotBlank(message = "Created Report ID không được để trống")
    private String createdReport;

    @NotBlank(message = "Reported User ID không được để trống")
    private String reportedUser;

    @NotNull(message = "Reported Job ID không được null")
    @Min(value = 1, message = "Reported Job ID phải lớn hơn 0")
    private int reportedJob;

}
