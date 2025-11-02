package com.project.it_job.service.imp;

import com.project.it_job.dto.LevelLanguageDto;
import com.project.it_job.entity.LevelLanguage;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.LevelLanguageMapper;
import com.project.it_job.repository.LevelLanguageRepository;
import com.project.it_job.request.LevelLanguageRequest;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.service.LevelLanguageService;
import com.project.it_job.util.PageCustomHelpper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LevelLanguageServiceImp implements LevelLanguageService {

    private final LevelLanguageRepository levelLanguageRepository;
    private final LevelLanguageMapper levelLanguageMapper;
    private final PageCustomHelpper pageCustomHelpper;

    @Override
    public List<LevelLanguageDto> getAllLevelLanguage() {
        return levelLanguageRepository.findAll()
                .stream()
                .map(levelLanguageMapper::toDto)
                .toList();
    }

    @Override
    public Page<LevelLanguageDto> getAllLevelLanguagePage(PageRequestCustom pageRequestCustom) {
        PageRequestCustom validated = pageCustomHelpper.validatePageCustom(pageRequestCustom);
        Pageable pageable = PageRequest.of(validated.getPageNumber() - 1, validated.getPageSize());
        return levelLanguageRepository.findAll(pageable).map(levelLanguageMapper::toDto);
    }

    @Override
    public LevelLanguageDto getLevelLanguageById(Integer id) {
        LevelLanguage levelLanguage = levelLanguageRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Khong tim thay id level_language"));
        return levelLanguageMapper.toDto(levelLanguage);
    }

    @Override
    public LevelLanguageDto createLevelLanguage(LevelLanguageRequest levelLanguageRequest) {
        LevelLanguage levelLanguage = LevelLanguage.builder()
                .name(levelLanguageRequest.getName())
                .build();
        levelLanguageRepository.save(levelLanguage);
        return levelLanguageMapper.toDto(levelLanguage);
    }

    @Override
    public LevelLanguageDto updateLevelLanguage(int id, LevelLanguageRequest levelLanguageRequest) {
        LevelLanguage levelLanguage = levelLanguageRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Khong tim thay id level_language"));

        levelLanguage.setName(levelLanguageRequest.getName() != null && !levelLanguageRequest.getName().isEmpty()
                ? levelLanguageRequest.getName()
                : levelLanguage.getName());

        LevelLanguage updated = levelLanguageRepository.save(levelLanguage);
        return levelLanguageMapper.toDto(updated);
    }

    @Override
    public LevelLanguageDto deleteLevelLanguage(int id) {
        LevelLanguage levelLanguage = levelLanguageRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Khong tim thay id level_language"));
        levelLanguageRepository.delete(levelLanguage);
        return levelLanguageMapper.toDto(levelLanguage);
    }
}
