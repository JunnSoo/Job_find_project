package com.project.it_job.controller;

import com.project.it_job.request.WardRequest;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.WardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ward")
@RequiredArgsConstructor
public class WardController {
    private final WardService wardService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(BaseResponse.success(wardService.getAll(),"OK"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return ResponseEntity.ok(BaseResponse.success(wardService.getById(id),"OK"));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody WardRequest wardRequest) {
        return ResponseEntity.ok(BaseResponse.success(wardService.create(wardRequest),"OK"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> update(@Valid @PathVariable int id, @RequestBody WardRequest wardRequest) {
        return ResponseEntity.ok(BaseResponse.success(wardService.update(id,wardRequest),"OK"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return ResponseEntity.ok(BaseResponse.success(wardService.delete(id),"OK"));
    }
}
