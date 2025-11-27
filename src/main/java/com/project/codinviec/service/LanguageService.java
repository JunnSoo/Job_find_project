package com.project.codinviec.service;

import com.project.codinviec.dto.LanguageDTO;
import com.project.codinviec.request.LanguageRequest;
import com.project.codinviec.request.PageRequestCustom;
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
