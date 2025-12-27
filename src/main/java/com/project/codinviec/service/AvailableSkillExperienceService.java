package com.project.codinviec.service;

import com.project.codinviec.dto.AvailableSkillExperienceDTO;
import com.project.codinviec.request.AvailableSkillExperienceRequest;
import com.project.codinviec.request.DeleteAvailableSkillExperienceByGroupCoreIdRequest;
import com.project.codinviec.request.PageRequestCustom;
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
    List<AvailableSkillExperienceDTO> deleteAvailableSkillExperienceByGroupCoreId( DeleteAvailableSkillExperienceByGroupCoreIdRequest request);

}
