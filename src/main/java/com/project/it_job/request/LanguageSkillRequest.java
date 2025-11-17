package com.project.it_job.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LanguageSkillRequest {
    @NotBlank(message = "User ID không được để trống")
    private String userId;

    @NotNull(message = "Language ID không được null")
    @Min(value = 1, message = "Language ID phải lớn hơn 0")
    private Integer languageId;

    @NotNull(message = "Level Language ID không được null")
    @Min(value = 1, message = "Level Language ID phải lớn hơn 0")
    private Integer levelLanguageId;
}
