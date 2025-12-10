package com.project.codinviec.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JobRequest {
    @NotBlank(message = "Vị trí công việc không được để trống")
    private String jobPosition;

    @NotBlank(message = "Company ID không được để trống")
    private String companyId;

    @NotBlank(message = "Địa chỉ chi tiết không được để trống")
    private String detailAddress;

    @NotBlank(message = "Mô tả công việc không được để trống")
    private String descriptionJob;

    @NotBlank(message = "Yêu cầu công việc không được để trống")
    private String requirement;

    @NotBlank(message = "Quyền lợi không được để trống")
    private String benefits;

    @NotNull(message = "Province ID không được null")
    @Min(value = 1, message = "Province ID phải lớn hơn 0")
    private int provinceId;

    @NotNull(message = "Industry ID không được null")
    @Min(value = 1, message = "Industry ID phải lớn hơn 0")
    private int industryId;

    @NotNull(message = "Job Level ID không được null")
    @Min(value = 1, message = "Job Level ID phải lớn hơn 0")
    private int jobLevelId;

    @NotNull(message = "Degree Level ID không được null")
    @Min(value = 1, message = "Degree Level ID phải lớn hơn 0")
    private int degreeLevelId;

    @NotNull(message = "Employment Type ID không được null")
    @Min(value = 1, message = "Employment Type ID phải lớn hơn 0")
    private int employmentTypeId;

    @NotNull(message = "Experience ID không được null")
    @Min(value = 1, message = "Experience ID phải lớn hơn 0")
    private int experienceId;

    private LocalDateTime createdDate;
}
