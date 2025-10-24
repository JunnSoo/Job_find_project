package com.project.it_job.mapper;

import com.project.it_job.dto.EmploymentTypeDTO;
import com.project.it_job.entity.EmploymentType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmploymentTypeMapper {
    EmploymentTypeMapper INSTANCE = Mappers.getMapper(com.project.it_job.mapper.EmploymentTypeMapper.class);
    EmploymentTypeDTO toDTO(EmploymentType employmentType);
    EmploymentType toEntity(EmploymentTypeDTO dto);
}
