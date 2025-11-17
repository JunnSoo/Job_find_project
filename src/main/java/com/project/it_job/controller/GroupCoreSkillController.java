package com.project.it_job.controller;

import com.project.it_job.request.GroupCoreSkillRequest;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.GroupCoreSkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/group-core-skill")
@RequiredArgsConstructor
public class GroupCoreSkillController {

    private final GroupCoreSkillService service;

    @GetMapping
    public ResponseEntity<?> getAllGroupCoreSkill(PageRequestCustom pageRequestCustom) {
        if (pageRequestCustom.getPageNumber() == 0
                && pageRequestCustom.getPageSize() == 0
                && pageRequestCustom.getKeyword() == null) {
            return ResponseEntity.ok(BaseResponse.success(service.getAllGroupCoreSkill(), "ok"));
        }
        return ResponseEntity.ok(BaseResponse.success(service.getAllGroupCoreSkillPage(pageRequestCustom), "ok"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGroupCoreSkillById(@PathVariable Integer id) {
        return ResponseEntity.ok(BaseResponse.success(service.getGroupCoreSkillById(id), "ok"));
    }

    @PostMapping
    public ResponseEntity<?> createGroupCoreSkill(@Valid @RequestBody GroupCoreSkillRequest request) {
        return ResponseEntity.ok(BaseResponse.success(service.createGroupCoreSkill(request), "ok"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGroupCoreSkill(@PathVariable Integer id,
                                                  @Valid @RequestBody GroupCoreSkillRequest request) {
        return ResponseEntity.ok(BaseResponse.success(service.updateGroupCoreSkill(id, request), "ok"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGroupCoreSkill(@PathVariable Integer id) {
        return ResponseEntity.ok(BaseResponse.success(service.deleteGroupCoreSkill(id), "ok"));
    }
}
