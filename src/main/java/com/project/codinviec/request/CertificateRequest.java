package com.project.codinviec.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificateRequest {
    @NotBlank(message = "User ID không được để trống")
    private String userId;

    @NotBlank(message = "Tên chứng chỉ không được để trống")
    private String certificateName;

    @NotBlank(message = "Tổ chức cấp không được để trống")
    private String organization;

    @NotNull(message = "Ngày cấp không được null")
    private LocalDate date;

    private String link;

    private String description;
}
