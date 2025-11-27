package com.project.codinviec.mapper;

import com.project.codinviec.dto.LanguageSkillDTO;
import com.project.codinviec.entity.Language;
import com.project.codinviec.entity.LanguageSkill;
import com.project.codinviec.entity.LevelLanguage;
import com.project.codinviec.entity.auth.User;
import com.project.codinviec.request.LanguageSkillRequest;
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
