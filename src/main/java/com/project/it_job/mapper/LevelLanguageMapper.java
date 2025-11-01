package com.project.it_job.mapper;

import com.project.it_job.dto.LevelLanguageDto;
import com.project.it_job.entity.LevelLanguage;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
@Builder
public class LevelLanguageMapper {
    public LevelLanguageDto toDto(LevelLanguage levelLanguage) {
        if (levelLanguage == null) return null;
        return LevelLanguageDto.builder()
                .id(levelLanguage.getId())
                .name(levelLanguage.getName())
                .build();
    }
}
