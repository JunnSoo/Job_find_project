package com.project.it_job.service;

import com.project.it_job.dto.AvailableSkillDto;
import com.project.it_job.request.AvailableSkillRequest;
import com.project.it_job.request.PageRequestCustom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AvailableSkillService {
    List<AvailableSkillDto> getAllAvailableSkill();
    Page<AvailableSkillDto> getAllAvailableSkillPage(PageRequestCustom pageRequestCustom);
    AvailableSkillDto getAvailableSkillById(Integer id);
    AvailableSkillDto createAvailableSkill(AvailableSkillRequest request);
    AvailableSkillDto updateAvailableSkill(int id, AvailableSkillRequest request);
    AvailableSkillDto deleteAvailableSkill(int id);
}
