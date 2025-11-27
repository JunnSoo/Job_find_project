package com.project.codinviec.service.imp;

import com.project.codinviec.dto.LanguageSkillDTO;
import com.project.codinviec.entity.Language;
import com.project.codinviec.entity.LanguageSkill;
import com.project.codinviec.entity.LevelLanguage;
import com.project.codinviec.entity.auth.User;
import com.project.codinviec.exception.common.NotFoundIdExceptionHandler;
import com.project.codinviec.mapper.LanguageSkillMapper;
import com.project.codinviec.repository.LanguageRepository;
import com.project.codinviec.repository.LanguageSkillRepository;
import com.project.codinviec.repository.LevelLanguageRepository;
import com.project.codinviec.repository.auth.UserRepository;
import com.project.codinviec.request.LanguageSkillRequest;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.service.LanguageSkillService;
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
public class LanguageSkillServiceImp implements LanguageSkillService {

    private final LanguageSkillRepository languageSkillRepository;
    private final LanguageRepository languageRepository;
    private final LevelLanguageRepository levelLanguageRepository;
    private final UserRepository userRepository;
    private final LanguageSkillMapper languageSkillMapper;
    private final PageCustomHelper pageCustomHelper;

    @Override
    public List<LanguageSkillDTO> getAllLanguageSkill() {
        return languageSkillRepository.findAll()
                .stream()
                .map(languageSkillMapper::toDto)
                .toList();
    }

    @Override
    public Page<LanguageSkillDTO> getAllLanguageSkillPage(PageRequestCustom pageRequestCustom) {
        PageRequestCustom validated = pageCustomHelper.validatePageCustom(pageRequestCustom);
        Pageable pageable = PageRequest.of(validated.getPageNumber() - 1, validated.getPageSize());
        return languageSkillRepository.findAll(pageable).map(languageSkillMapper::toDto);
    }

    @Override
    public List<LanguageSkillDTO> getLanguageSkillByUser(String userId) {
        return languageSkillRepository.findByUser_Id(userId)
                .stream()
                .map(languageSkillMapper::toDto)
                .toList();
    }

    @Override
    public LanguageSkillDTO getLanguageSkillById(Integer id) {
        LanguageSkill ls = languageSkillRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id language_skill"));
        return languageSkillMapper.toDto(ls);
    }

    @Override
    @Transactional
    public LanguageSkillDTO createLanguageSkill(LanguageSkillRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id user"));
        Language language = languageRepository.findById(request.getLanguageId())
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id language"));
        LevelLanguage level = levelLanguageRepository.findById(request.getLevelLanguageId())
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id level_language"));

        if (languageSkillRepository.existsByUser_IdAndLanguage_Id(user.getId(), language.getId())) {
            throw new NotFoundIdExceptionHandler("User đã có skill với language này");
        }

        LanguageSkill newSkill = languageSkillMapper.saveLanguageSkill(user, language, level, request);
        languageSkillRepository.save(newSkill);
        return languageSkillMapper.toDto(newSkill);
    }

    @Override
    @Transactional
    public LanguageSkillDTO updateLanguageSkill(int id, LanguageSkillRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id user"));
        languageSkillRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id language_skill"));

        Language language = languageRepository.findById(request.getLanguageId())
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id language"));
        LevelLanguage level = levelLanguageRepository.findById(request.getLevelLanguageId())
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id level_language"));

        LanguageSkill ls = languageSkillMapper.updateLanguageSkill(id, user, language, level, request);
        LanguageSkill updated = languageSkillRepository.save(ls);
        return languageSkillMapper.toDto(updated);
    }

    @Override
    @Transactional
    public LanguageSkillDTO deleteLanguageSkill(int id) {
        LanguageSkill ls = languageSkillRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id language_skill"));
        languageSkillRepository.delete(ls);
        return languageSkillMapper.toDto(ls);
    }
}
