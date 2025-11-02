package com.project.it_job.service.imp;

import com.project.it_job.dto.ProjectDto;
import com.project.it_job.entity.Project;
import com.project.it_job.entity.auth.User;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.ProjectMapper;
import com.project.it_job.repository.ProjectRepository;
import com.project.it_job.repository.auth.UserRepository;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.ProjectRequest;
import com.project.it_job.service.ProjectService;
import com.project.it_job.util.PageCustomHelpper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImp implements ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectMapper projectMapper;
    private final PageCustomHelpper pageCustomHelpper;

    @Override
    public List<ProjectDto> getAllProject() {
        return projectRepository.findAll()
                .stream()
                .map(projectMapper::toDto)
                .toList();
    }

    @Override
    public Page<ProjectDto> getAllProjectPage(PageRequestCustom pageRequestCustom) {
        PageRequestCustom pageRequestValidate = pageCustomHelpper.validatePageCustom(pageRequestCustom);
        System.out.println("pageRequestValidate:"+pageRequestValidate.getPageNumber());
        Pageable pageable = PageRequest.of(pageRequestValidate.getPageNumber()-1, pageRequestValidate.getPageSize());

        return projectRepository.findAll(pageable).map(projectMapper::toDto);
    }

    @Override
    public ProjectDto getProjectById(Integer id) {
        Project project = projectRepository.findById(id).orElseThrow(()-> new NotFoundIdExceptionHandler("Khong tim thay id project"));
        return projectMapper.toDto(project);
    }

    @Override
    public ProjectDto createProject(ProjectRequest projectRequest) {
        User user = userRepository.findById(projectRequest.getUserId())
                .orElseThrow(()-> new NotFoundIdExceptionHandler("Khong tim thay id user"));
        Project project = Project.builder()
                .name(projectRequest.getName())
                .user(user)
                .startDate(projectRequest.getStartDate())
                .endDate(projectRequest.getEndDate())
                .projectUrl(projectRequest.getProjectUrl())
                .company(projectRequest.getCompany())
                .build();
        projectRepository.save(project);

        return projectMapper.toDto(project);
    }

    @Override
    public ProjectDto updateProject(int id,ProjectRequest projectRequest) {
        User user = userRepository.findById(projectRequest.getUserId())
                .orElseThrow(()-> new NotFoundIdExceptionHandler("Khong tim thay id user"));
        Project project = projectRepository.findById(id)
                .orElseThrow(()-> new NotFoundIdExceptionHandler("Khong tim thay id project"));

        project.setName(projectRequest.getName() != null && !projectRequest.getName().isEmpty()
                ? projectRequest.getName()
                : project.getName());

        project.setStartDate(projectRequest.getStartDate() != null
                ? projectRequest.getStartDate()
                : project.getStartDate());

        project.setEndDate(projectRequest.getEndDate() != null
                ? projectRequest.getEndDate()
                : project.getEndDate());

        project.setProjectUrl(projectRequest.getProjectUrl() != null && !projectRequest.getProjectUrl().isEmpty()
                ? projectRequest.getProjectUrl()
                : project.getProjectUrl());

        project.setCompany(projectRequest.getCompany() != null && !projectRequest.getCompany().isEmpty()
                ? projectRequest.getCompany()
                : project.getCompany());

        Project updatedProject = projectRepository.save(project);
        return projectMapper.toDto(updatedProject);
    }

    @Override
    public ProjectDto deleteProject(int id) {
        Project project = projectRepository.findById(id).orElseThrow(()-> new NotFoundIdExceptionHandler("Khong tim thay id project"));
        projectRepository.delete(project);
        return projectMapper.toDto(project);
    }
}
