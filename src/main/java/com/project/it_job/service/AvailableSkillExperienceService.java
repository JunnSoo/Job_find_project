package com.project.it_job.service;

import com.project.it_job.dto.AvailableSkillExperienceDto;
import com.project.it_job.request.AvailableSkillExperienceRequest;
import com.project.it_job.request.PageRequestCustom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AvailableSkillExperienceService {

    List<AvailableSkillExperienceDto> getAllAvailableSkillExperience();

    Page<AvailableSkillExperienceDto> getAllAvailableSkillExperiencePage(PageRequestCustom pageRequestCustom);

    List<AvailableSkillExperienceDto> getAvailableSkillExperienceByUser(String userId);

    AvailableSkillExperienceDto getAvailableSkillExperienceById(Integer id);

    AvailableSkillExperienceDto createAvailableSkillExperience(AvailableSkillExperienceRequest request);

    AvailableSkillExperienceDto updateAvailableSkillExperience(int id, AvailableSkillExperienceRequest request);

    AvailableSkillExperienceDto deleteAvailableSkillExperience(int id);
}
