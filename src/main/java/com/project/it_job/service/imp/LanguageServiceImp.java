package com.project.it_job.service.imp;

import com.project.it_job.dto.LanguageDto;
import com.project.it_job.entity.Language;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.LanguageMapper;
import com.project.it_job.repository.LanguageRepository;
import com.project.it_job.request.LanguageRequest;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.service.LanguageService;
import com.project.it_job.util.PageCustomHelpper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageServiceImp implements LanguageService {

    private final LanguageRepository languageRepository;
    private final LanguageMapper languageMapper;
    private final PageCustomHelpper pageCustomHelpper;

    @Override
    public List<LanguageDto> getAllLanguage() {
        return languageRepository.findAll()
                .stream()
                .map(languageMapper::toDto)
                .toList();
    }

    @Override
    public Page<LanguageDto> getAllLanguagePage(PageRequestCustom pageRequestCustom) {
        PageRequestCustom pageRequestValidate = pageCustomHelpper.validatePageCustom(pageRequestCustom);
        Pageable pageable = PageRequest.of(pageRequestValidate.getPageNumber() - 1, pageRequestValidate.getPageSize());
        return languageRepository.findAll(pageable).map(languageMapper::toDto);
    }

    @Override
    public LanguageDto getLanguageById(Integer id) {
        Language language = languageRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Khong tim thay id language"));
        return languageMapper.toDto(language);
    }

    @Override
    public LanguageDto createLanguage(LanguageRequest languageRequest) {
        Language language = Language.builder()
                .name(languageRequest.getName())
                .build();
        languageRepository.save(language);
        return languageMapper.toDto(language);
    }

    @Override
    public LanguageDto updateLanguage(int id, LanguageRequest languageRequest) {
        Language language = languageRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Khong tim thay id language"));

        language.setName(languageRequest.getName() != null && !languageRequest.getName().isEmpty()
                ? languageRequest.getName()
                : language.getName());

        Language updatedLanguage = languageRepository.save(language);
        return languageMapper.toDto(updatedLanguage);
    }

    @Override
    public LanguageDto deleteLanguage(int id) {
        Language language = languageRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Khong tim thay id language"));
        languageRepository.delete(language);
        return languageMapper.toDto(language);
    }
}
