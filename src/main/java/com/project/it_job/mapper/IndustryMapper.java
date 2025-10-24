package com.project.it_job.mapper;

import com.project.it_job.dto.IndustryDTO;
import com.project.it_job.entity.Industry;
import org.springframework.stereotype.Component;

@Component
public class IndustryMapper {

    public IndustryDTO toDTO(Industry entity) {
        if (entity == null) return null;
        IndustryDTO dto = new IndustryDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public Industry toEntity(IndustryDTO dto) {
        if (dto == null) return null;
        Industry entity = new Industry();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}
