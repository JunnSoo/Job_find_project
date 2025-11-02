package com.project.it_job.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvailableSkillExperienceDto {
    private int id;

    private String userId;

    private Integer groupCoreId;
    private String groupCoreName;

    private Integer availableSkillId;
    private String availableSkillName;

    private Integer experienceId;
    private String experienceName;
}
