package com.project.codinviec.mapper;

import com.project.codinviec.dto.JobLevelDTO;
import com.project.codinviec.entity.JobLevel;
import com.project.codinviec.request.JobLevelRequest;
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
