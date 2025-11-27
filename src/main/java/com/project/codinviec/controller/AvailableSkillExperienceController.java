package com.project.codinviec.controller;

import com.project.codinviec.request.AvailableSkillExperienceRequest;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.response.BaseResponse;
import com.project.codinviec.service.AvailableSkillExperienceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/available-skill-experience")
@RequiredArgsConstructor
public class AvailableSkillExperienceController {

    private final AvailableSkillExperienceService availableSkillExperienceService;

    @GetMapping
    public ResponseEntity<?> getAllAvailableSkillExperience(PageRequestCustom pageRequestCustom) {
        if (pageRequestCustom.getPageNumber() == 0
                && pageRequestCustom.getPageSize() == 0
                && pageRequestCustom.getKeyword() == null) {
            return ResponseEntity.ok(
                    BaseResponse.success(availableSkillExperienceService.getAllAvailableSkillExperience(), "ok")
            );
        }
        return ResponseEntity.ok(
                BaseResponse.success(availableSkillExperienceService.getAllAvailableSkillExperiencePage(pageRequestCustom), "ok")
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAvailableSkillExperienceById(@PathVariable Integer id) {
        return ResponseEntity.ok(
                BaseResponse.success(availableSkillExperienceService.getAvailableSkillExperienceById(id), "ok")
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAvailableSkillExperienceByUser(@PathVariable String userId) {
        return ResponseEntity.ok(
                BaseResponse.success(availableSkillExperienceService.getAvailableSkillExperienceByUser(userId), "ok")
        );
    }

    @PostMapping
    public ResponseEntity<?> createAvailableSkillExperience(@Valid @RequestBody AvailableSkillExperienceRequest request) {
        return ResponseEntity.ok(
                BaseResponse.success(availableSkillExperienceService.createAvailableSkillExperience(request), "ok")
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAvailableSkillExperience(@PathVariable Integer id,
                                                            @Valid @RequestBody AvailableSkillExperienceRequest request) {
        return ResponseEntity.ok(
                BaseResponse.success(availableSkillExperienceService.updateAvailableSkillExperience(id, request), "ok")
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAvailableSkillExperience(@PathVariable Integer id) {
        return ResponseEntity.ok(
                BaseResponse.success(availableSkillExperienceService.deleteAvailableSkillExperience(id), "ok")
        );
    }
}
