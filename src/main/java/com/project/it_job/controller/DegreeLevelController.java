package com.project.it_job.controller;

import com.project.it_job.request.DegreeLevelRequest;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.DegreeLevelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/degree-level")
@RequiredArgsConstructor
public class DegreeLevelController {

    private final DegreeLevelService degreeLevelService;

    @GetMapping
    public ResponseEntity<?> getAll(PageRequestCustom pageRequestCustom) {
        if (pageRequestCustom.getPageNumber() == 0 && pageRequestCustom.getPageSize() == 0 && pageRequestCustom.getKeyword() == null) {
            return ResponseEntity.ok(BaseResponse.success(degreeLevelService.getAll(), "OK"));
        }
        return ResponseEntity.ok(BaseResponse.success(degreeLevelService.getAllWithPage(pageRequestCustom), "OK"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return ResponseEntity.ok(BaseResponse.success(degreeLevelService.getById(id), "OK"));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody DegreeLevelRequest dto) {
        return ResponseEntity.ok(BaseResponse.success(degreeLevelService.create(dto), "Tạo DegreeLevel thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @Valid @RequestBody DegreeLevelRequest dto) {
        return ResponseEntity.ok(BaseResponse.success(degreeLevelService.update(id, dto), "Cập nhật DegreeLevel thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        degreeLevelService.delete(id);
        return ResponseEntity.ok(BaseResponse.success(null, "Xóa Degree có ID " + id + " thành công"));
    }
}
