package com.project.codinviec.controller;

import com.project.codinviec.request.ExperienceRequest;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.response.BaseResponse;
import com.project.codinviec.service.ExperienceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/experience")
@RequiredArgsConstructor
public class ExperienceController {

    private final ExperienceService experienceService;

    @GetMapping
    public ResponseEntity<?> getAllExperience(PageRequestCustom pageRequestCustom) {
        if (pageRequestCustom.getPageNumber() == 0
                && pageRequestCustom.getPageSize() == 0
                && pageRequestCustom.getKeyword() == null) {
            return ResponseEntity.ok(BaseResponse.success(experienceService.getAllExperience(), "ok"));
        }
        return ResponseEntity.ok(BaseResponse.success(experienceService.getAllExperiencePage(pageRequestCustom), "ok"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExperienceById(@PathVariable Integer id) {
        return ResponseEntity.ok(BaseResponse.success(experienceService.getExperienceById(id), "ok"));
    }

    @PostMapping
    public ResponseEntity<?> createExperience(@Valid @RequestBody ExperienceRequest request) {
        return ResponseEntity.ok(BaseResponse.success(experienceService.createExperience(request), "ok"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateExperience(@PathVariable Integer id,
                                              @Valid @RequestBody ExperienceRequest request) {
        return ResponseEntity.ok(BaseResponse.success(experienceService.updateExperience(id, request), "ok"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExperience(@PathVariable Integer id) {
        return ResponseEntity.ok(BaseResponse.success(experienceService.deleteExperience(id), "ok"));
    }
}
