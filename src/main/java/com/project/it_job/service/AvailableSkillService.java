package com.project.it_job.service;

import com.project.it_job.dto.AvailableSkillDTO;
import com.project.it_job.request.AvailableSkillRequest;
import com.project.it_job.request.PageRequestCustom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AvailableSkillService {
    List<AvailableSkillDTO> getAllAvailableSkill();
    Page<AvailableSkillDTO> getAllAvailableSkillPage(PageRequestCustom pageRequestCustom);
    AvailableSkillDTO getAvailableSkillById(Integer id);
    AvailableSkillDTO createAvailableSkill(AvailableSkillRequest request);
    AvailableSkillDTO updateAvailableSkill(int id, AvailableSkillRequest request);
    AvailableSkillDTO deleteAvailableSkill(int id);
}
