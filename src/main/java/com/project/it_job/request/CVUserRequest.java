package com.project.it_job.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CVUserRequest {

    @NotBlank(message = "Candidate không được để trống")
    private String candidateId;

    private MultipartFile fileUrl;

    @NotBlank(message = "Tiêu đề (title) không được để trống")
    private String title;

    @NotNull(message = "Trạng thái hoạt động (isActive) không được để trống")
    private Boolean isActive;
}
