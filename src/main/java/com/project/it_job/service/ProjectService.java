package com.project.it_job.service;

import com.project.it_job.dto.ProjectDto;
import com.project.it_job.entity.Project;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.ProjectRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProjectService {
    List<ProjectDto> getAllProject();
    Page<ProjectDto> getAllProjectPage(PageRequestCustom pageRequestCustom);
    ProjectDto getProjectById(Integer id);
    ProjectDto createProject(ProjectRequest projectRequest);
    ProjectDto updateProject(int id,ProjectRequest projectRequest);
    ProjectDto deleteProject(int id);
}
