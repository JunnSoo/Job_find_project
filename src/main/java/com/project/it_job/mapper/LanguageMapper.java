package com.project.it_job.mapper;

import com.project.it_job.dto.LanguageDTO;
import com.project.it_job.entity.Language;
import com.project.it_job.request.LanguageRequest;
import org.springframework.stereotype.Component;

@Component
public class LanguageMapper {

    public LanguageDTO toDto(Language language) {
        if (language == null) return null;
        return LanguageDTO.builder()
                .id(language.getId())
                .name(language.getName())
                .build();
    }

    public Language saveLanguage(LanguageRequest request) {
        if (request == null) return null;
        return Language.builder()
                .name(request.getName())
                .build();
    }

    public Language updateLanguage(Integer id, LanguageRequest request) {
        if (request == null) return null;
        return Language.builder()
                .id(id)
                .name(request.getName())
                .build();
    }
}
