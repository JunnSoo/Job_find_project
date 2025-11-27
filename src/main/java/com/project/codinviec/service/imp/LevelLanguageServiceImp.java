package com.project.codinviec.service.imp;

import com.project.codinviec.dto.LevelLanguageDTO;
import com.project.codinviec.entity.LevelLanguage;
import com.project.codinviec.exception.common.NotFoundIdExceptionHandler;
import com.project.codinviec.mapper.LevelLanguageMapper;
import com.project.codinviec.repository.LevelLanguageRepository;
import com.project.codinviec.request.LevelLanguageRequest;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.service.LevelLanguageService;
import com.project.codinviec.util.helper.PageCustomHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LevelLanguageServiceImp implements LevelLanguageService {

    private final LevelLanguageRepository levelLanguageRepository;
    private final LevelLanguageMapper levelLanguageMapper;
    private final PageCustomHelper pageCustomHelper;

    @Override
    public List<LevelLanguageDTO> getAllLevelLanguage() {
        return levelLanguageRepository.findAll()
                .stream()
                .map(levelLanguageMapper::toDto)
                .toList();
    }

    @Override
    public Page<LevelLanguageDTO> getAllLevelLanguagePage(PageRequestCustom pageRequestCustom) {
        PageRequestCustom validated = pageCustomHelper.validatePageCustom(pageRequestCustom);
        Pageable pageable = PageRequest.of(validated.getPageNumber() - 1, validated.getPageSize());
        return levelLanguageRepository.findAll(pageable).map(levelLanguageMapper::toDto);
    }

    @Override
    public LevelLanguageDTO getLevelLanguageById(Integer id) {
        LevelLanguage levelLanguage = levelLanguageRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id level_language"));
        return levelLanguageMapper.toDto(levelLanguage);
    }

    @Override
    @Transactional
    public LevelLanguageDTO createLevelLanguage(LevelLanguageRequest levelLanguageRequest) {
        LevelLanguage levelLanguage = levelLanguageMapper.saveLevelLanguage(levelLanguageRequest);
        levelLanguageRepository.save(levelLanguage);
        return levelLanguageMapper.toDto(levelLanguage);
    }

    @Override
    @Transactional
    public LevelLanguageDTO updateLevelLanguage(int id, LevelLanguageRequest levelLanguageRequest) {
        levelLanguageRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id level_language"));

        LevelLanguage entity = levelLanguageMapper.updateLevelLanguage(id, levelLanguageRequest);
        LevelLanguage updated = levelLanguageRepository.save(entity);
        return levelLanguageMapper.toDto(updated);
    }

    @Override
    @Transactional
    public LevelLanguageDTO deleteLevelLanguage(int id) {
        LevelLanguage levelLanguage = levelLanguageRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id level_language"));
        levelLanguageRepository.delete(levelLanguage);
        return levelLanguageMapper.toDto(levelLanguage);
    }
}
