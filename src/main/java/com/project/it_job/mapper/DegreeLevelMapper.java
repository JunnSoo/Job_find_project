package com.project.it_job.mapper;

import com.project.it_job.dto.DegreeLevelDTO;
import com.project.it_job.entity.DegreeLevel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DegreeLevelMapper {
    DegreeLevelDTO toDTO(DegreeLevel degreeLevel);
    DegreeLevel toEntity(DegreeLevelDTO dto);
}
