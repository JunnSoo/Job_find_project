package com.project.codinviec.mapper;

import com.project.codinviec.dto.LevelLanguageDTO;
import com.project.codinviec.entity.LevelLanguage;
import com.project.codinviec.request.LevelLanguageRequest;
import org.springframework.stereotype.Component;

@Component
public class LevelLanguageMapper {
    public LevelLanguageDTO toDto(LevelLanguage levelLanguage) {
        if (levelLanguage == null) return null;
        return LevelLanguageDTO.builder()
                .id(levelLanguage.getId())
                .name(levelLanguage.getName())
                .build();
    }

    public LevelLanguage saveLevelLanguage(LevelLanguageRequest request) {
        if (request == null) return null;
        return LevelLanguage.builder()
                .name(request.getName())
                .build();
    }

    public LevelLanguage updateLevelLanguage(Integer id, LevelLanguageRequest request) {
        if (request == null) return null;
        return LevelLanguage.builder()
                .id(id)
                .name(request.getName())
                .build();
    }
}
