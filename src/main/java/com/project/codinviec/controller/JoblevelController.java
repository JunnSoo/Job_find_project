package com.project.codinviec.controller;

import com.project.codinviec.request.JobLevelRequest;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.response.BaseResponse;
import com.project.codinviec.service.JobLevelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/job-level")
@RequiredArgsConstructor
public class JoblevelController {

    private final JobLevelService jobLevelService;

    @GetMapping
    public ResponseEntity<?> getAll(PageRequestCustom pageRequestCustom) {
        if (pageRequestCustom.getPageNumber() == 0 && pageRequestCustom.getPageSize() == 0 && pageRequestCustom.getKeyword() == null) {
            return ResponseEntity.ok(BaseResponse.success(jobLevelService.getAll(), "OK"));
        }
        return ResponseEntity.ok(BaseResponse.success(jobLevelService.getAllWithPage(pageRequestCustom), "OK"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return ResponseEntity.ok(BaseResponse.success(jobLevelService.getById(id), "OK"));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody JobLevelRequest request) {
        return ResponseEntity.ok(BaseResponse.success(jobLevelService.create(request), "Tạo JobLevel thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @Valid @RequestBody JobLevelRequest request) {
        return ResponseEntity.ok(BaseResponse.success(jobLevelService.update(id, request), "Cập nhật Joblevel thành công"));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        jobLevelService.delete(id);
        return ResponseEntity.ok(BaseResponse.success(null, "Xóa Joblevel có ID " + id + " thành công"));
    }
}
