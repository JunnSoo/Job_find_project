package com.project.it_job.service.imp;

import com.project.it_job.dto.ExperienceDTO;
import com.project.it_job.entity.Experience;
import com.project.it_job.exception.common.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.ExperienceMapper;
import com.project.it_job.repository.ExperienceRepository;
import com.project.it_job.request.ExperienceRequest;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.service.ExperienceService;
import com.project.it_job.util.helper.PageCustomHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImp implements ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final ExperienceMapper experienceMapper;
    private final PageCustomHelper pageCustomHelper;

    @Override
    public List<ExperienceDTO> getAllExperience() {
        return experienceRepository.findAll()
                .stream()
                .map(experienceMapper::toDto)
                .toList();
    }

    @Override
    public Page<ExperienceDTO> getAllExperiencePage(PageRequestCustom pageRequestCustom) {
        PageRequestCustom validated = pageCustomHelper.validatePageCustom(pageRequestCustom);
        Pageable pageable = PageRequest.of(validated.getPageNumber() - 1, validated.getPageSize());
        return experienceRepository.findAll(pageable).map(experienceMapper::toDto);
    }

    @Override
    public ExperienceDTO getExperienceById(Integer id) {
        Experience entity = experienceRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id experience"));
        return experienceMapper.toDto(entity);
    }

    @Override
    @Transactional
    public ExperienceDTO createExperience(ExperienceRequest request) {
        Experience entity = experienceMapper.saveExperience(request);
        experienceRepository.save(entity);
        return experienceMapper.toDto(entity);
    }

    @Override
    @Transactional
    public ExperienceDTO updateExperience(int id, ExperienceRequest request) {
        experienceRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id experience"));

        Experience entity = experienceMapper.updateExperience(id, request);
        Experience updated = experienceRepository.save(entity);
        return experienceMapper.toDto(updated);
    }

    @Override
    @Transactional
    public ExperienceDTO deleteExperience(int id) {
        Experience entity = experienceRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id experience"));
        experienceRepository.delete(entity);
        return experienceMapper.toDto(entity);
    }
}
