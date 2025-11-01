package com.project.it_job.service;

import com.project.it_job.dto.LanguageDto;
import com.project.it_job.request.LanguageRequest;
import com.project.it_job.request.PageRequestCustom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LanguageService {
    List<LanguageDto> getAllLanguage();
    Page<LanguageDto> getAllLanguagePage(PageRequestCustom pageRequestCustom);
    LanguageDto getLanguageById(Integer id);
    LanguageDto createLanguage(LanguageRequest languageRequest);
    LanguageDto updateLanguage(int id, LanguageRequest languageRequest);
    LanguageDto deleteLanguage(int id);
}
