package com.project.codinviec.service;

import com.project.codinviec.dto.SoftSkillsNameDTO;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.SaveUpdateSoftSkillNameRequest;
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
