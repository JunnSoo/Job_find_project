package com.project.it_job.service.imp;

import com.project.it_job.dto.LanguageSkillDTO;
import com.project.it_job.entity.Language;
import com.project.it_job.entity.LanguageSkill;
import com.project.it_job.entity.LevelLanguage;
import com.project.it_job.entity.auth.User;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.LanguageSkillMapper;
import com.project.it_job.repository.LanguageRepository;
import com.project.it_job.repository.LanguageSkillRepository;
import com.project.it_job.repository.LevelLanguageRepository;
import com.project.it_job.repository.auth.UserRepository;
import com.project.it_job.request.LanguageSkillRequest;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.service.LanguageSkillService;
import com.project.it_job.util.PageCustomHelpper;
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
    private final PageCustomHelpper pageCustomHelpper;

    @Override
    public List<LanguageSkillDTO> getAllLanguageSkill() {
        return languageSkillRepository.findAll()
                .stream()
                .map(languageSkillMapper::toDto)
                .toList();
    }

    @Override
    public Page<LanguageSkillDTO> getAllLanguageSkillPage(PageRequestCustom pageRequestCustom) {
        PageRequestCustom validated = pageCustomHelpper.validatePageCustom(pageRequestCustom);
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
