package com.project.it_job.mapper;

import com.project.it_job.dto.JobLevelDTO;
import com.project.it_job.entity.JobLevel;
import org.springframework.stereotype.Component;

@Component
public class JobLevelMapper {

    public JobLevelDTO toDTO(JobLevel jobLevel) {
        if (jobLevel == null) {
            return null;
        }
        JobLevelDTO dto = new JobLevelDTO();
        dto.setId(jobLevel.getId());
        dto.setName(jobLevel.getName());
        return dto;
    }

    public JobLevel toEntity(JobLevelDTO dto) {
        if (dto == null) {
            return null;
        }
        JobLevel entity = new JobLevel();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}
