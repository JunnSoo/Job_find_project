package com.project.it_job.service;

import com.project.it_job.dto.ExperienceDTO;
import com.project.it_job.request.ExperienceRequest;
import com.project.it_job.request.PageRequestCustom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ExperienceService {
    List<ExperienceDTO> getAllExperience();
    Page<ExperienceDTO> getAllExperiencePage(PageRequestCustom pageRequestCustom);
    ExperienceDTO getExperienceById(Integer id);
    ExperienceDTO createExperience(ExperienceRequest request);
    ExperienceDTO updateExperience(int id, ExperienceRequest request);
    ExperienceDTO deleteExperience(int id);
}
