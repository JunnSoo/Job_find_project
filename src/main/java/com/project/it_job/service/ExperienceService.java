package com.project.it_job.service;

import com.project.it_job.dto.ExperienceDto;
import com.project.it_job.request.ExperienceRequest;
import com.project.it_job.request.PageRequestCustom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ExperienceService {
    List<ExperienceDto> getAllExperience();
    Page<ExperienceDto> getAllExperiencePage(PageRequestCustom pageRequestCustom);
    ExperienceDto getExperienceById(Integer id);
    ExperienceDto createExperience(ExperienceRequest request);
    ExperienceDto updateExperience(int id, ExperienceRequest request);
    ExperienceDto deleteExperience(int id);
}
