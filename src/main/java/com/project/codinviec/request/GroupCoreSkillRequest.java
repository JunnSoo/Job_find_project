package com.project.codinviec.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupCoreSkillRequest {
    @NotBlank(message = "Tên nhóm kỹ năng không được để trống")
    private String name;
}
