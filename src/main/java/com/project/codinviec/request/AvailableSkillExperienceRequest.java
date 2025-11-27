package com.project.codinviec.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableSkillExperienceRequest {
    @NotBlank(message = "User ID không được để trống")
    private String userId;

    @NotNull(message = "Group Core ID không được null")
    @Min(value = 1, message = "Group Core ID phải lớn hơn 0")
    private Integer groupCoreId;

    @NotNull(message = "Available Skill ID không được null")
    @Min(value = 1, message = "Available Skill ID phải lớn hơn 0")
    private Integer availableSkillId;

    @NotNull(message = "Experience ID không được null")
    @Min(value = 1, message = "Experience ID phải lớn hơn 0")
    private Integer experienceId;
}
