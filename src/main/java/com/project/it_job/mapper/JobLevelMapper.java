package com.project.it_job.mapper;

import com.project.it_job.dto.JobLevelDTO;
import com.project.it_job.entity.JobLevel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobLevelMapper {
    JobLevelDTO toDTO(JobLevel jobLevel);
    JobLevel toEntity(JobLevelDTO dto);
}
