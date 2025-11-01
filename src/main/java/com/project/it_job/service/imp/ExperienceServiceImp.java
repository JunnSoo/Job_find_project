package com.project.it_job.service.imp;

import com.project.it_job.dto.ExperienceDto;
import com.project.it_job.entity.Experience;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.ExperienceMapper;
import com.project.it_job.repository.ExperienceRepository;
import com.project.it_job.request.ExperienceRequest;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.service.ExperienceService;
import com.project.it_job.util.PageCustomHelpper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImp implements ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final ExperienceMapper experienceMapper;
    private final PageCustomHelpper pageCustomHelpper;

    @Override
    public List<ExperienceDto> getAllExperience() {
        return experienceRepository.findAll()
                .stream()
                .map(experienceMapper::toDto)
                .toList();
    }

    @Override
    public Page<ExperienceDto> getAllExperiencePage(PageRequestCustom pageRequestCustom) {
        PageRequestCustom validated = pageCustomHelpper.validatePageCustom(pageRequestCustom);
        Pageable pageable = PageRequest.of(validated.getPageNumber() - 1, validated.getPageSize());
        return experienceRepository.findAll(pageable).map(experienceMapper::toDto);
    }

    @Override
    public ExperienceDto getExperienceById(Integer id) {
        Experience entity = experienceRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Khong tim thay id experience"));
        return experienceMapper.toDto(entity);
    }

    @Override
    public ExperienceDto createExperience(ExperienceRequest request) {
        Experience entity = Experience.builder()
                .name(request.getName())
                .build();
        experienceRepository.save(entity);
        return experienceMapper.toDto(entity);
    }

    @Override
    public ExperienceDto updateExperience(int id, ExperienceRequest request) {
        Experience entity = experienceRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Khong tim thay id experience"));

        entity.setName(request.getName() != null && !request.getName().isEmpty()
                ? request.getName()
                : entity.getName());

        Experience updated = experienceRepository.save(entity);
        return experienceMapper.toDto(updated);
    }

    @Override
    public ExperienceDto deleteExperience(int id) {
        Experience entity = experienceRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Khong tim thay id experience"));
        experienceRepository.delete(entity);
        return experienceMapper.toDto(entity);
    }
}
