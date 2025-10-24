package com.project.it_job.mapper;

import com.project.it_job.dto.DegreeLevelDTO;
import com.project.it_job.entity.DegreeLevel;
import org.springframework.stereotype.Component;

@Component
public class DegreeLevelMapper {

    public DegreeLevelDTO toDTO(DegreeLevel entity) {
        if (entity == null) return null;
        DegreeLevelDTO dto = new DegreeLevelDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public DegreeLevel toEntity(DegreeLevelDTO dto) {
        if (dto == null) return null;
        DegreeLevel entity = new DegreeLevel();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}
