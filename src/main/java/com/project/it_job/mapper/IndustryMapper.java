package com.project.it_job.mapper;

import com.project.it_job.dto.IndustryDTO;
import com.project.it_job.entity.Industry;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface IndustryMapper {
    IndustryMapper INSTANCE = Mappers.getMapper(com.project.it_job.mapper.IndustryMapper.class);

        IndustryDTO toDTO(Industry industry);
        Industry toEntity(IndustryDTO dto);


}
