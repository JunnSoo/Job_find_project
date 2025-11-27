package com.project.codinviec.service;

import com.project.codinviec.dto.ExperienceDTO;
import com.project.codinviec.request.ExperienceRequest;
import com.project.codinviec.request.PageRequestCustom;
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
