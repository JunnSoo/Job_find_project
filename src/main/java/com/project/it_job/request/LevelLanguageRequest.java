package com.project.it_job.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LevelLanguageRequest {
    @NotBlank(message = "Tên cấp độ ngôn ngữ không được để trống")
    private String name;
}
