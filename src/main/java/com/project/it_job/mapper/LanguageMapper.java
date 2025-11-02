package com.project.it_job.mapper;

import com.project.it_job.dto.LanguageDto;
import com.project.it_job.entity.Language;
import org.springframework.stereotype.Component;
import lombok.Builder;

@Component
@Builder
public class LanguageMapper {

    public LanguageDto toDto(Language language) {
        if (language == null) return null;
        return LanguageDto.builder()
                .id(language.getId())
                .name(language.getName())
                .build();
    }
}
