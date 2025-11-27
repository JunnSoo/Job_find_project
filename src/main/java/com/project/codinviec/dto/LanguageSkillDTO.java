package com.project.codinviec.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LanguageSkillDTO {
    private int id;
    private String userId;
    private Integer languageId;
    private String languageName;
    private Integer levelLanguageId;
    private String levelLanguageName;
}
