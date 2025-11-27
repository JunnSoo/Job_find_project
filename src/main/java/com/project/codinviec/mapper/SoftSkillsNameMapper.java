package com.project.codinviec.mapper;

import com.project.codinviec.dto.SoftSkillsNameDTO;
import com.project.codinviec.entity.SoftSkillsName;
import com.project.codinviec.request.SaveUpdateSoftSkillNameRequest;
import org.springframework.stereotype.Component;

@Component
public class SoftSkillsNameMapper {
    public SoftSkillsNameDTO softSkillsNameToSoftSkillsNameDTO(SoftSkillsName softSkillsName) {
        return SoftSkillsNameDTO.builder()
                .id(softSkillsName.getId())
                .name(softSkillsName.getName())
                .build();
    }

    public SoftSkillsName saveSoftSkillsName(SaveUpdateSoftSkillNameRequest saveUpdateSoftSkillNameRequest) {
        return SoftSkillsName.builder()
                .name(saveUpdateSoftSkillNameRequest.getName())
                .build();
    }

    public SoftSkillsName updateSoftSkillsName(Integer idSoftSkillName ,SaveUpdateSoftSkillNameRequest saveUpdateSoftSkillNameRequest) {
        return SoftSkillsName.builder()
                .id(idSoftSkillName)
                .name(saveUpdateSoftSkillNameRequest.getName())
                .build();
    }


}
