package com.project.it_job.mapper;

import com.project.it_job.dto.LanguageSkillDto;
import com.project.it_job.entity.LanguageSkill;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
@Builder
public class LanguageSkillMapper {

    public LanguageSkillDto toDto(LanguageSkill ls) {
        if (ls == null) return null;
        return LanguageSkillDto.builder()
                .id(ls.getId())
                .userId(ls.getUser() != null ? ls.getUser().getId() : null)
                .languageId(ls.getLanguage() != null ? ls.getLanguage().getId() : null)
                .languageName(ls.getLanguage() != null ? ls.getLanguage().getName() : null)
                .levelLanguageId(ls.getLevelLanguage() != null ? ls.getLevelLanguage().getId() : null)
                .levelLanguageName(ls.getLevelLanguage() != null ? ls.getLevelLanguage().getName() : null)
                .build();
    }
}
