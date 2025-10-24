package com.project.it_job.mapper;

import com.project.it_job.dto.CompanySizeDTO;
import com.project.it_job.entity.CompanySize;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanySizeMapper {
    CompanySizeDTO toDTO(CompanySize companySize);
    CompanySize toEntity(CompanySizeDTO companySizeDTO);
}
