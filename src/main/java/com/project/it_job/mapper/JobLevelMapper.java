package com.project.it_job.mapper;

import com.project.it_job.dto.JobLevelDTO;
import com.project.it_job.entity.JobLevel;
import com.project.it_job.request.JobLevelRequest;
import org.springframework.stereotype.Component;

@Component
public class JobLevelMapper {
    public JobLevelDTO toDTO(JobLevel entity) {
        if(entity == null) return null;
        return JobLevelDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
    
    public JobLevel toEntity(JobLevelDTO dto) {
        if(dto == null) return null;
        return JobLevel.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

    public JobLevel saveJobLevel(JobLevelRequest request) {
        if (request == null) return null;
        return JobLevel.builder()
                .name(request.getName())
                .build();
    }

    public JobLevel updateJobLevel(Integer id, JobLevelRequest request) {
        if (request == null) return null;
        return JobLevel.builder()
                .id(id)
                .name(request.getName())
                .build();
    }
}
