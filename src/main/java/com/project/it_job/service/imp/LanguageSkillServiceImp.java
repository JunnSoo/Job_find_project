package com.project.it_job.service.imp;

import com.project.it_job.dto.LanguageSkillDto;
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
    public List<LanguageSkillDto> getAllLanguageSkill() {
        return languageSkillRepository.findAll()
                .stream()
                .map(languageSkillMapper::toDto)
                .toList();
    }

    @Override
    public Page<LanguageSkillDto> getAllLanguageSkillPage(PageRequestCustom pageRequestCustom) {
        PageRequestCustom validated = pageCustomHelpper.validatePageCustom(pageRequestCustom);
        Pageable pageable = PageRequest.of(validated.getPageNumber() - 1, validated.getPageSize());
        return languageSkillRepository.findAll(pageable).map(languageSkillMapper::toDto);
    }

    @Override
    public List<LanguageSkillDto> getLanguageSkillByUser(String userId) {
        return languageSkillRepository.findByUser_Id(userId)
                .stream()
                .map(languageSkillMapper::toDto)
                .toList();
    }

    @Override
    public LanguageSkillDto getLanguageSkillById(Integer id) {
        LanguageSkill ls = languageSkillRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Khong tim thay id language_skill"));
        return languageSkillMapper.toDto(ls);
    }

    @Override
    public LanguageSkillDto createLanguageSkill(LanguageSkillRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Khong tim thay id user"));
        Language language = languageRepository.findById(request.getLanguageId())
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Khong tim thay id language"));
        LevelLanguage level = levelLanguageRepository.findById(request.getLevelLanguageId())
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Khong tim thay id level_language"));

        if (languageSkillRepository.existsByUser_IdAndLanguage_Id(user.getId(), language.getId())) {
            throw new NotFoundIdExceptionHandler("User da co skill voi language nay");
        }

        LanguageSkill newSkill = LanguageSkill.builder()
                .user(user)
                .language(language)
                .levelLanguage(level)
                .build();

        languageSkillRepository.save(newSkill);
        return languageSkillMapper.toDto(newSkill);
    }

    @Override
    public LanguageSkillDto updateLanguageSkill(int id, LanguageSkillRequest request) {
        LanguageSkill ls = languageSkillRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Khong tim thay id language_skill"));

        if (request.getLanguageId() != null) {
            Language language = languageRepository.findById(request.getLanguageId())
                    .orElseThrow(() -> new NotFoundIdExceptionHandler("Khong tim thay id language"));
            ls.setLanguage(language);
        }

        if (request.getLevelLanguageId() != null) {
            LevelLanguage level = levelLanguageRepository.findById(request.getLevelLanguageId())
                    .orElseThrow(() -> new NotFoundIdExceptionHandler("Khong tim thay id level_language"));
            ls.setLevelLanguage(level);
        }

        LanguageSkill updated = languageSkillRepository.save(ls);
        return languageSkillMapper.toDto(updated);
    }

    @Override
    public LanguageSkillDto deleteLanguageSkill(int id) {
        LanguageSkill ls = languageSkillRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Khong tim thay id language_skill"));
        languageSkillRepository.delete(ls);
        return languageSkillMapper.toDto(ls);
    }
}
