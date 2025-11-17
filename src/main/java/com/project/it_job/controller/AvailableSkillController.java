package com.project.it_job.controller;

import com.project.it_job.request.AvailableSkillRequest;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.AvailableSkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/available-skill")
@RequiredArgsConstructor
public class AvailableSkillController {

    private final AvailableSkillService availableSkillService;

    @GetMapping
    public ResponseEntity<?> getAllAvailableSkill(PageRequestCustom pageRequestCustom) {
        if (pageRequestCustom.getPageNumber() == 0
                && pageRequestCustom.getPageSize() == 0
                && pageRequestCustom.getKeyword() == null) {
            return ResponseEntity.ok(
                    BaseResponse.success(availableSkillService.getAllAvailableSkill(), "ok")
            );
        }
        return ResponseEntity.ok(
                BaseResponse.success(availableSkillService.getAllAvailableSkillPage(pageRequestCustom), "ok")
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAvailableSkillById(@PathVariable Integer id) {
        return ResponseEntity.ok(
                BaseResponse.success(availableSkillService.getAvailableSkillById(id), "ok")
        );
    }

    @PostMapping
    public ResponseEntity<?> createAvailableSkill(@Valid @RequestBody AvailableSkillRequest request) {
        return ResponseEntity.ok(
                BaseResponse.success(availableSkillService.createAvailableSkill(request), "ok")
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAvailableSkill(@PathVariable Integer id,
                                                  @Valid @RequestBody AvailableSkillRequest request) {
        return ResponseEntity.ok(
                BaseResponse.success(availableSkillService.updateAvailableSkill(id, request), "ok")
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAvailableSkill(@PathVariable Integer id) {
        return ResponseEntity.ok(
                BaseResponse.success(availableSkillService.deleteAvailableSkill(id), "ok")
        );
    }
}
