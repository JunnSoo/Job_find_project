package com.project.codinviec.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveUpdateSoftSkillNameRequest {
    @NotBlank(message = "name không được để trống")
    private String name;
}
