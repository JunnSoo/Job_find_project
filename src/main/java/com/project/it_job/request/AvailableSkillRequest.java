package com.project.it_job.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableSkillRequest {
    @NotBlank(message = "Tên kỹ năng không được để trống")
    private String name;
}
