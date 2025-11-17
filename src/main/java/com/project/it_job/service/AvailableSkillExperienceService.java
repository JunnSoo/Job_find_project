package com.project.it_job.service;

import com.project.it_job.dto.AvailableSkillExperienceDTO;
import com.project.it_job.request.AvailableSkillExperienceRequest;
import com.project.it_job.request.PageRequestCustom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AvailableSkillExperienceService {

    List<AvailableSkillExperienceDTO> getAllAvailableSkillExperience();

    Page<AvailableSkillExperienceDTO> getAllAvailableSkillExperiencePage(PageRequestCustom pageRequestCustom);

    List<AvailableSkillExperienceDTO> getAvailableSkillExperienceByUser(String userId);

    AvailableSkillExperienceDTO getAvailableSkillExperienceById(Integer id);

    AvailableSkillExperienceDTO createAvailableSkillExperience(AvailableSkillExperienceRequest request);

    AvailableSkillExperienceDTO updateAvailableSkillExperience(int id, AvailableSkillExperienceRequest request);

    AvailableSkillExperienceDTO deleteAvailableSkillExperience(int id);
}
