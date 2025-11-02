package com.project.it_job.request;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LanguageSkillRequest {
    private String userId;
    private Integer languageId;
    private Integer levelLanguageId;
}
