package com.project.codinviec.controller;

import com.project.codinviec.request.StatusSpecialRequest;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.response.BaseResponse;
import com.project.codinviec.service.StatusSpecialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/status-special")
@RequiredArgsConstructor
public class StatusSpecialController {

    private final StatusSpecialService statusSpecialService;

    @GetMapping
    public ResponseEntity<?> getAll(PageRequestCustom pageRequestCustom) {
        if (pageRequestCustom.getPageNumber() == 0 && pageRequestCustom.getPageSize() == 0 && pageRequestCustom.getKeyword() == null) {
            return ResponseEntity.ok(BaseResponse.success(statusSpecialService.getAll(), "OK"));
        }
        return ResponseEntity.ok(BaseResponse.success(statusSpecialService.getAllWithPage(pageRequestCustom), "OK"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return ResponseEntity.ok(BaseResponse.success(statusSpecialService.getById(id), "OK"));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody StatusSpecialRequest dto) {
        return ResponseEntity.ok(BaseResponse.success(statusSpecialService.create(dto), "Tạo StatusSpecial thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @Valid @RequestBody StatusSpecialRequest dto) {
        return ResponseEntity.ok(BaseResponse.success(statusSpecialService.update(id, dto), "Cập nhật StatusSpecial thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return ResponseEntity.ok(BaseResponse.success(statusSpecialService.delete(id), "Xóa StatusSpecial có ID " + id + " thành công"));
    }
}

