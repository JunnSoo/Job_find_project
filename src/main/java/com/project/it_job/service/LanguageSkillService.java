package com.project.it_job.service;

import com.project.it_job.dto.LanguageSkillDTO;
import com.project.it_job.request.LanguageSkillRequest;
import com.project.it_job.request.PageRequestCustom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LanguageSkillService {
    List<LanguageSkillDTO> getAllLanguageSkill();
    Page<LanguageSkillDTO> getAllLanguageSkillPage(PageRequestCustom pageRequestCustom);
    List<LanguageSkillDTO> getLanguageSkillByUser(String userId);
    LanguageSkillDTO getLanguageSkillById(Integer id);
    LanguageSkillDTO createLanguageSkill(LanguageSkillRequest request);
    LanguageSkillDTO updateLanguageSkill(int id, LanguageSkillRequest request);
    LanguageSkillDTO deleteLanguageSkill(int id);
}
