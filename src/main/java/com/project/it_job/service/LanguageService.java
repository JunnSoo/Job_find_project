package com.project.it_job.service;

import com.project.it_job.dto.LanguageDTO;
import com.project.it_job.request.LanguageRequest;
import com.project.it_job.request.PageRequestCustom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LanguageService {
    List<LanguageDTO> getAllLanguage();
    Page<LanguageDTO> getAllLanguagePage(PageRequestCustom pageRequestCustom);
    LanguageDTO getLanguageById(Integer id);
    LanguageDTO createLanguage(LanguageRequest languageRequest);
    LanguageDTO updateLanguage(int id, LanguageRequest languageRequest);
    LanguageDTO deleteLanguage(int id);
}
