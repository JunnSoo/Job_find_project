package com.project.it_job.mapper;

import com.project.it_job.dto.ProjectDto;
import com.project.it_job.entity.Project;
import com.project.it_job.request.ProjectRequest;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
@Builder
public class ProjectMapper {
    public ProjectDto toDto(Project project) {
        if (project == null) return null;
        return ProjectDto.builder()
                .id(project.getId())
                .userId(project.getUser().getId())
                .name(project.getName())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .company(project.getCompany())
                .build();
    }
}
