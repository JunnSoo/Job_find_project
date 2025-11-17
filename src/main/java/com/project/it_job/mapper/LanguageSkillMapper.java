package com.project.it_job.mapper;

import com.project.it_job.dto.LanguageSkillDTO;
import com.project.it_job.entity.Language;
import com.project.it_job.entity.LanguageSkill;
import com.project.it_job.entity.LevelLanguage;
import com.project.it_job.entity.auth.User;
import com.project.it_job.request.LanguageSkillRequest;
import org.springframework.stereotype.Component;

@Component
public class LanguageSkillMapper {

    public LanguageSkillDTO toDto(LanguageSkill ls) {
        if (ls == null) return null;
        return LanguageSkillDTO.builder()
                .id(ls.getId())
                .userId(ls.getUser() != null ? ls.getUser().getId() : null)
                .languageId(ls.getLanguage() != null ? ls.getLanguage().getId() : null)
                .languageName(ls.getLanguage() != null ? ls.getLanguage().getName() : null)
                .levelLanguageId(ls.getLevelLanguage() != null ? ls.getLevelLanguage().getId() : null)
                .levelLanguageName(ls.getLevelLanguage() != null ? ls.getLevelLanguage().getName() : null)
                .build();
    }

    public LanguageSkill saveLanguageSkill(User user, Language language, LevelLanguage levelLanguage, LanguageSkillRequest request) {
        if (request == null) return null;
        return LanguageSkill.builder()
                .user(user)
                .language(language)
                .levelLanguage(levelLanguage)
                .build();
    }

    public LanguageSkill updateLanguageSkill(Integer id, User user, Language language, LevelLanguage levelLanguage, LanguageSkillRequest request) {
        if (request == null) return null;
        return LanguageSkill.builder()
                .id(id)
                .user(user)
                .language(language)
                .levelLanguage(levelLanguage)
                .build();
    }
}
