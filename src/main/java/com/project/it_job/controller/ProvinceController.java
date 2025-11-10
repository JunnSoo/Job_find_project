package com.project.it_job.controller;

import com.project.it_job.request.ProvinceRequest;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.ProvinceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/province")
@RequiredArgsConstructor
public class ProvinceController {

    private final ProvinceService provinceService;

    @GetMapping
    public ResponseEntity<BaseResponse> getAll() {
        return ResponseEntity.ok(BaseResponse.success(provinceService.getAll(),"OK"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return ResponseEntity.ok(BaseResponse.success(provinceService.getById(id), "OK"));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ProvinceRequest request) {
        return ResponseEntity.ok(BaseResponse.success(provinceService.create(request), "OK"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable Integer id, @RequestBody ProvinceRequest request) {
        return ResponseEntity.ok(BaseResponse.success(provinceService.update(id,request),"OK"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return ResponseEntity.ok(BaseResponse.success(provinceService.delete(id), "OK"));
    }
}
