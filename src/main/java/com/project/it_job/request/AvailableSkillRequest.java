package com.project.it_job.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvailableSkillRequest {
    @NotEmpty(message = "Tên không được để trống")
    private String name;
}
