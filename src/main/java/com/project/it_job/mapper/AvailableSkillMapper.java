package com.project.it_job.mapper;

import com.project.it_job.dto.AvailableSkillDTO;
import com.project.it_job.entity.AvailableSkill;
import com.project.it_job.request.AvailableSkillRequest;
import org.springframework.stereotype.Component;

@Component
public class AvailableSkillMapper {

    public AvailableSkillDTO toDto(AvailableSkill entity) {
        if (entity == null) return null;
        return AvailableSkillDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public AvailableSkill saveAvailableSkill(AvailableSkillRequest request) {
        if (request == null) return null;
        return AvailableSkill.builder()
                .name(request.getName())
                .build();
    }

    public AvailableSkill updateAvailableSkill(Integer id, AvailableSkillRequest request) {
        if (request == null) return null;
        return AvailableSkill.builder()
                .id(id)
                .name(request.getName())
                .build();
    }
}
