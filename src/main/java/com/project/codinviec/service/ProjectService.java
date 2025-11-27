package com.project.codinviec.service;

import com.project.codinviec.dto.ProjectDTO;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.ProjectRequest;
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
