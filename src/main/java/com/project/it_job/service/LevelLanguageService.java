package com.project.it_job.service;

import com.project.it_job.dto.LevelLanguageDTO;
import com.project.it_job.request.LevelLanguageRequest;
import com.project.it_job.request.PageRequestCustom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LevelLanguageService {
    List<LevelLanguageDTO> getAllLevelLanguage();
    Page<LevelLanguageDTO> getAllLevelLanguagePage(PageRequestCustom pageRequestCustom);
    LevelLanguageDTO getLevelLanguageById(Integer id);
    LevelLanguageDTO createLevelLanguage(LevelLanguageRequest levelLanguageRequest);
    LevelLanguageDTO updateLevelLanguage(int id, LevelLanguageRequest levelLanguageRequest);
    LevelLanguageDTO deleteLevelLanguage(int id);
}
