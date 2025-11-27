package com.project.codinviec.service;

import com.project.codinviec.dto.LanguageSkillDTO;
import com.project.codinviec.request.LanguageSkillRequest;
import com.project.codinviec.request.PageRequestCustom;
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
