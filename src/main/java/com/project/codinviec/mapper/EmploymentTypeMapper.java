package com.project.codinviec.mapper;

import com.project.codinviec.dto.EmploymentTypeDTO;
import com.project.codinviec.entity.EmploymentType;
import com.project.codinviec.request.EmploymentTypeRequest;
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
