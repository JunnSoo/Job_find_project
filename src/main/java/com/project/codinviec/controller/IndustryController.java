package com.project.codinviec.controller;

import com.project.codinviec.request.IndustryRequest;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.response.BaseResponse;
import com.project.codinviec.service.IndustryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/industry")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class IndustryController {

    private final IndustryService industryService;

    @GetMapping
    public ResponseEntity<?> getAll(PageRequestCustom pageRequestCustom) {
        if (pageRequestCustom.getPageNumber() == 0 && pageRequestCustom.getPageSize() == 0 && pageRequestCustom.getKeyword() == null) {
            return ResponseEntity.ok(BaseResponse.success(industryService.getAll(), "OK"));
        }
        return ResponseEntity.ok(BaseResponse.success(industryService.getAllWithPage(pageRequestCustom), "OK"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
       return ResponseEntity.ok(BaseResponse.success(industryService.getById(id), "OK"));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody IndustryRequest request) {
        return ResponseEntity.ok(BaseResponse.success(industryService.create(request), "Tạo industry thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @Valid @RequestBody IndustryRequest request) {
        return ResponseEntity.ok(BaseResponse.success(industryService.update(id, request), "Cập nhật industry thành công"));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        industryService.delete(id);
        return ResponseEntity.ok(BaseResponse.success(null,"Xóa industry có ID "+ id + "thành công"));
    }
}
