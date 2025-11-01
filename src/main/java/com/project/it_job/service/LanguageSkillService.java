package com.project.it_job.service;

import com.project.it_job.dto.LanguageSkillDto;
import com.project.it_job.request.LanguageSkillRequest;
import com.project.it_job.request.PageRequestCustom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LanguageSkillService {
    List<LanguageSkillDto> getAllLanguageSkill();
    Page<LanguageSkillDto> getAllLanguageSkillPage(PageRequestCustom pageRequestCustom);
    List<LanguageSkillDto> getLanguageSkillByUser(String userId);
    LanguageSkillDto getLanguageSkillById(Integer id);
    LanguageSkillDto createLanguageSkill(LanguageSkillRequest request);
    LanguageSkillDto updateLanguageSkill(int id, LanguageSkillRequest request);
    LanguageSkillDto deleteLanguageSkill(int id);
}
