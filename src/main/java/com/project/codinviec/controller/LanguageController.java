package com.project.codinviec.controller;

import com.project.codinviec.request.LanguageRequest;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.response.BaseResponse;
import com.project.codinviec.service.LanguageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/language")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageService languageService;

    @GetMapping
    public ResponseEntity<?> getAllLanguage(PageRequestCustom pageRequestCustom) {
        if (pageRequestCustom.getPageNumber() == 0
                && pageRequestCustom.getPageSize() == 0
                && pageRequestCustom.getKeyword() == null) {
            return ResponseEntity.ok(
                    BaseResponse.success(languageService.getAllLanguage(), "ok")
            );
        }
        return ResponseEntity.ok(
                BaseResponse.success(languageService.getAllLanguagePage(pageRequestCustom), "ok")
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLanguageById(@PathVariable Integer id) {
        return ResponseEntity.ok(
                BaseResponse.success(languageService.getLanguageById(id), "ok")
        );
    }

    @PostMapping
    public ResponseEntity<?> createLanguage(@Valid @RequestBody LanguageRequest languageRequest) {
        return ResponseEntity.ok(
                BaseResponse.success(languageService.createLanguage(languageRequest), "ok")
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLanguage(@PathVariable Integer id,
                                            @Valid @RequestBody LanguageRequest languageRequest) {
        return ResponseEntity.ok(
                BaseResponse.success(languageService.updateLanguage(id, languageRequest), "ok")
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLanguage(@PathVariable Integer id) {
        return ResponseEntity.ok(
                BaseResponse.success(languageService.deleteLanguage(id), "ok")
        );
    }
}
