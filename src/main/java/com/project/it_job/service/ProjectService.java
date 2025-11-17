package com.project.it_job.service;

import com.project.it_job.dto.ProjectDTO;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.ProjectRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProjectService {
    List<ProjectDTO> getAllProject();
    Page<ProjectDTO> getAllProjectPage(PageRequestCustom pageRequestCustom);
    ProjectDTO getProjectById(Integer id);
    ProjectDTO createProject(ProjectRequest projectRequest);
    ProjectDTO updateProject(int id, ProjectRequest projectRequest);
    ProjectDTO deleteProject(int id);
}
