package com.project.it_job.service;

import com.project.it_job.dto.LevelLanguageDto;
import com.project.it_job.request.LevelLanguageRequest;
import com.project.it_job.request.PageRequestCustom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LevelLanguageService {
    List<LevelLanguageDto> getAllLevelLanguage();
    Page<LevelLanguageDto> getAllLevelLanguagePage(PageRequestCustom pageRequestCustom);
    LevelLanguageDto getLevelLanguageById(Integer id);
    LevelLanguageDto createLevelLanguage(LevelLanguageRequest levelLanguageRequest);
    LevelLanguageDto updateLevelLanguage(int id, LevelLanguageRequest levelLanguageRequest);
    LevelLanguageDto deleteLevelLanguage(int id);
}
