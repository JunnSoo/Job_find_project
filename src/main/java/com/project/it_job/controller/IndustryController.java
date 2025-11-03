package com.project.it_job.controller;

import com.project.it_job.request.IndustryRequest;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.IndustryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/industry")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class IndustryController {

    private final IndustryService industryService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(BaseResponse.success(industryService.getAll(), "OK"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
       return ResponseEntity.ok(BaseResponse.success(industryService.getById(id), "OK"));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody IndustryRequest request) {
        return ResponseEntity.ok(BaseResponse.success(industryService.create(request), "Tạo industry thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody IndustryRequest request) {
        return ResponseEntity.ok(BaseResponse.success(industryService.update(id, request), "Cập nhật industry thành công"));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        industryService.delete(id);
        return ResponseEntity.ok(BaseResponse.success(null,"Xóa industry có ID "+ id + "thành công"));
    }
}
