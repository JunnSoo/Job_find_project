package com.project.codinviec.service;

import com.project.codinviec.dto.AvailableSkillDTO;
import com.project.codinviec.request.AvailableSkillRequest;
import com.project.codinviec.request.PageRequestCustom;
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
