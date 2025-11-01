package com.project.it_job.controller;

import com.project.it_job.request.LanguageSkillRequest;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.LanguageSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/language-skill")
@RequiredArgsConstructor
public class LanguageSkillController {

    private final LanguageSkillService languageSkillService;

    @GetMapping
    public ResponseEntity<?> getAllLanguageSkill(PageRequestCustom pageRequestCustom) {
        if (pageRequestCustom.getPageNumber() == 0
                && pageRequestCustom.getPageSize() == 0
                && pageRequestCustom.getKeyword() == null) {
            return ResponseEntity.ok(BaseResponse.success(languageSkillService.getAllLanguageSkill(), "ok"));
        }
        return ResponseEntity.ok(BaseResponse.success(languageSkillService.getAllLanguageSkillPage(pageRequestCustom), "ok"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLanguageSkillById(@PathVariable Integer id) {
        return ResponseEntity.ok(BaseResponse.success(languageSkillService.getLanguageSkillById(id), "ok"));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getLanguageSkillByUser(@PathVariable String userId) {
        return ResponseEntity.ok(BaseResponse.success(languageSkillService.getLanguageSkillByUser(userId), "ok"));
    }

    @PostMapping
    public ResponseEntity<?> createLanguageSkill(@RequestBody LanguageSkillRequest request) {
        return ResponseEntity.ok(BaseResponse.success(languageSkillService.createLanguageSkill(request), "ok"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLanguageSkill(@PathVariable Integer id,
                                                 @RequestBody LanguageSkillRequest request) {
        return ResponseEntity.ok(BaseResponse.success(languageSkillService.updateLanguageSkill(id, request), "ok"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLanguageSkill(@PathVariable Integer id) {
        return ResponseEntity.ok(BaseResponse.success(languageSkillService.deleteLanguageSkill(id), "ok"));
    }
}
