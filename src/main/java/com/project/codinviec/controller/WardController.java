package com.project.codinviec.controller;

import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.WardRequest;
import com.project.codinviec.response.BaseResponse;
import com.project.codinviec.service.WardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ward")
@RequiredArgsConstructor
public class WardController {
    private final WardService wardService;

    @GetMapping
    public ResponseEntity<BaseResponse> getAll(PageRequestCustom pageRequestCustom) {
        if (pageRequestCustom.getPageNumber() == 0 && pageRequestCustom.getPageSize() == 0 && pageRequestCustom.getKeyword() == null) {
            return ResponseEntity.ok(BaseResponse.success(wardService.getAll(), "Lấy danh sách ward thành công"));
        }
        return ResponseEntity.ok(BaseResponse.success(wardService.getAllWithPage(pageRequestCustom), "Lấy danh sách ward thành công"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getById(@PathVariable int id) {
        return ResponseEntity.ok(BaseResponse.success(wardService.getById(id), "Lấy ward thành công"));
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@Valid @RequestBody WardRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponse.success(wardService.create(request), "Tạo ward thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable int id, @Valid @RequestBody WardRequest request) {
        return ResponseEntity.ok(BaseResponse.success(wardService.update(id, request), "Cập nhật ward thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable int id) {
        wardService.delete(id);
        return ResponseEntity.ok(BaseResponse.success(null, "Xóa ward có ID " + id + " thành công"));
    }
}
