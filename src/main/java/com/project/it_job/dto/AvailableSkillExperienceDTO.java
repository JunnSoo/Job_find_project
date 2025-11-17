package com.project.it_job.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvailableSkillExperienceDTO {
    private int id;

    private String userId;

    private Integer groupCoreId;
    private String groupCoreName;

    private Integer availableSkillId;
    private String availableSkillName;

    private Integer experienceId;
    private String experienceName;
}
