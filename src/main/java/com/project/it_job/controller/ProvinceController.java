package com.project.it_job.controller;

import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.ProvinceRequest;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.ProvinceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/province")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProvinceController {

    private final ProvinceService provinceService;

    @GetMapping
    public ResponseEntity<?> getAll(PageRequestCustom pageRequestCustom) {
        if (pageRequestCustom.getPageNumber() == 0 && pageRequestCustom.getPageSize() == 0
                && pageRequestCustom.getKeyword() == null) {
            return ResponseEntity.ok(BaseResponse.success(provinceService.getAll(), "OK"));
        }
        return ResponseEntity.ok(BaseResponse.success(provinceService.getAllWithPage(pageRequestCustom), "OK"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return ResponseEntity.ok(BaseResponse.success(provinceService.getById(id), "OK"));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ProvinceRequest request) {
        return ResponseEntity.ok(BaseResponse.success(provinceService.create(request), "Tạo province thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @Valid @RequestBody ProvinceRequest request) {
        return ResponseEntity
                .ok(BaseResponse.success(provinceService.update(id, request), "Cập nhật province thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        provinceService.delete(id);
        return ResponseEntity.ok(BaseResponse.success(null, "Xóa province có ID " + id + " thành công"));
    }
}
