package com.project.it_job.request;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvailableSkillExperienceRequest {
    private String userId;
    private Integer groupCoreId;
    private Integer availableSkillId;
    private Integer experienceId;
}
