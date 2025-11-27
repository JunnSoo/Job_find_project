package com.project.codinviec.controller;

import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.ProjectRequest;
import com.project.codinviec.response.BaseResponse;
import com.project.codinviec.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<?> getAllProject(PageRequestCustom pageRequestCustom) {
        if(pageRequestCustom.getPageNumber() == 0 &&pageRequestCustom.getPageSize() == 0 && pageRequestCustom.getKeyword() == null) {
            return ResponseEntity.ok(BaseResponse.success(projectService.getAllProject(),"ok"));
        }
        return ResponseEntity.ok(BaseResponse.success(projectService.getAllProjectPage(pageRequestCustom),"ok"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable Integer id) {
        return ResponseEntity.ok(BaseResponse.success(projectService.getProjectById(id),"ok"));
    }

    @PostMapping
    public ResponseEntity<?> createProject(@Valid @RequestBody ProjectRequest projectRequest) {
        return ResponseEntity.ok(BaseResponse.success(projectService.createProject(projectRequest),"ok"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProject(@PathVariable Integer id, @Valid @RequestBody ProjectRequest projectRequest) {
        return ResponseEntity.ok(BaseResponse.success(projectService.updateProject(id,projectRequest),"ok"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Integer id) {
        return ResponseEntity.ok(BaseResponse.success(projectService.deleteProject(id),"ok"));
    }
}
