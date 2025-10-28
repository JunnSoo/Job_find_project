package com.project.it_job.mapper;

import com.project.it_job.dto.EmploymentTypeDTO;
import com.project.it_job.entity.EmploymentType;
import org.springframework.stereotype.Component;

@Component
public class EmploymentTypeMapper {

    public static EmploymentTypeDTO toDTO(EmploymentType entity) {
        if (entity == null) return null;
        EmploymentTypeDTO dto = new EmploymentTypeDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public static EmploymentType toEntity(EmploymentTypeDTO dto) {
        if (dto == null) return null;
        EmploymentType entity = new EmploymentType();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}
