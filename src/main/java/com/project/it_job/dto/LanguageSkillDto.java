package com.project.it_job.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LanguageSkillDto {
    private int id;
    private String userId;
    private Integer languageId;
    private String languageName;
    private Integer levelLanguageId;
    private String levelLanguageName;
}
