package com.project.it_job.service;

import com.project.it_job.dto.SoftSkillsNameDTO;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.SaveUpdateSoftSkillNameRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SoftSkillsNameService {
    List<SoftSkillsNameDTO> getAllSoftSkillsName();
    Page<SoftSkillsNameDTO> getAllSoftSkillsNamePage(PageRequestCustom pageRequestCustom);
    SoftSkillsNameDTO getSoftSkillsNameById(Integer idSoftSkillsName);
    SoftSkillsNameDTO saveSoftSkillsName(SaveUpdateSoftSkillNameRequest saveUpdateSoftSkillNameRequest);
    SoftSkillsNameDTO updateSoftSkillsName(Integer idSoftSkillsName,SaveUpdateSoftSkillNameRequest saveUpdateSoftSkillNameRequest);
    SoftSkillsNameDTO deleteSoftSkillsNameById(Integer idSoftSkillsName);
    List<SoftSkillsNameDTO> getSoftSkillsNameByUserId(String idUser);
}
