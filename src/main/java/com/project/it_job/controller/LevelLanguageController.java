package com.project.it_job.controller;

import com.project.it_job.request.LevelLanguageRequest;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.LevelLanguageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/level-language")
@RequiredArgsConstructor
public class LevelLanguageController {

    private final LevelLanguageService levelLanguageService;

    @GetMapping
    public ResponseEntity<?> getAllLevelLanguage(PageRequestCustom pageRequestCustom) {
        if (pageRequestCustom.getPageNumber() == 0
                && pageRequestCustom.getPageSize() == 0
                && pageRequestCustom.getKeyword() == null) {
            return ResponseEntity.ok(BaseResponse.success(levelLanguageService.getAllLevelLanguage(), "ok"));
        }
        return ResponseEntity.ok(BaseResponse.success(levelLanguageService.getAllLevelLanguagePage(pageRequestCustom), "ok"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLevelLanguageById(@PathVariable Integer id) {
        return ResponseEntity.ok(BaseResponse.success(levelLanguageService.getLevelLanguageById(id), "ok"));
    }

    @PostMapping
    public ResponseEntity<?> createLevelLanguage(@Valid @RequestBody LevelLanguageRequest levelLanguageRequest) {
        return ResponseEntity.ok(BaseResponse.success(levelLanguageService.createLevelLanguage(levelLanguageRequest), "ok"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLevelLanguage(@PathVariable Integer id,
                                                 @Valid @RequestBody LevelLanguageRequest levelLanguageRequest) {
        return ResponseEntity.ok(BaseResponse.success(levelLanguageService.updateLevelLanguage(id, levelLanguageRequest), "ok"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLevelLanguage(@PathVariable Integer id) {
        return ResponseEntity.ok(BaseResponse.success(levelLanguageService.deleteLevelLanguage(id), "ok"));
    }
}
