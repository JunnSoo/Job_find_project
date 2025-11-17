package com.project.it_job.controller;

import com.project.it_job.request.CVUserRequest;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.CVUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cv-users")
public class CVUserController {
    private final CVUserService cvUserService;

    @GetMapping
    public ResponseEntity<?> getAll(PageRequestCustom pageRequestCustom) {

        if (pageRequestCustom.getPageNumber() == 0
                && pageRequestCustom.getPageSize() == 0
                && pageRequestCustom.getKeyword() == null
                && pageRequestCustom.getSortBy() == null) {
            return ResponseEntity.ok(BaseResponse.success(cvUserService.getAll(), "OK"));
        }

        return ResponseEntity.ok(BaseResponse.success(cvUserService.getAllWithPage(pageRequestCustom), "OK"));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> create(@ModelAttribute @Valid CVUserRequest cvUserRequest) {
        return ResponseEntity.ok(BaseResponse.success(cvUserService.create(cvUserRequest), "Tạo CV thành công"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable Integer id) {
        return ResponseEntity.ok(BaseResponse.success(cvUserService.getById(id), "OK"));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> update(
            @PathVariable Integer id,
            @ModelAttribute @Valid CVUserRequest cvUserRequest) {
        return ResponseEntity
                .ok(BaseResponse.success(cvUserService.update(id, cvUserRequest), "Cập nhật CV thành công"));
    }

    @DeleteMapping("/{id}/{candidateId}")
    public ResponseEntity<?> delete(@PathVariable Integer id, @PathVariable String candidateId) {
        return ResponseEntity.ok(BaseResponse.success(cvUserService.deleteById(id, candidateId), "OK"));
    }
}
