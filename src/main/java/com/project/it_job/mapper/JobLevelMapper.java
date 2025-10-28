package com.project.it_job.mapper;

import com.project.it_job.dto.JobLevelDTO;
import com.project.it_job.entity.JobLevel;

public class JobLevelMapper {
    public static JobLevelDTO toDTO(JobLevel entity) {
        if(entity == null) return null;
        JobLevelDTO dto = new JobLevelDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }
    public static JobLevel toEntity(JobLevelDTO dto) {
        if(dto == null) return null;
        JobLevel entity = new JobLevel();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}
