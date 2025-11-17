package com.project.it_job.mapper;

import com.project.it_job.dto.EmploymentTypeDTO;
import com.project.it_job.entity.EmploymentType;
import com.project.it_job.request.EmploymentTypeRequest;
import org.springframework.stereotype.Component;

@Component
public class EmploymentTypeMapper {

    public EmploymentTypeDTO toDTO(EmploymentType entity) {
        if (entity == null) return null;
        return EmploymentTypeDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public EmploymentType toEntity(EmploymentTypeDTO dto) {
        if (dto == null) return null;
        return EmploymentType.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

    public EmploymentType saveEmploymentType(EmploymentTypeRequest request) {
        if (request == null) return null;
        return EmploymentType.builder()
                .name(request.getName())
                .build();
    }

    public EmploymentType updateEmploymentType(Integer id, EmploymentTypeRequest request) {
        if (request == null) return null;
        return EmploymentType.builder()
                .id(id)
                .name(request.getName())
                .build();
    }
}
