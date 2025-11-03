package com.project.it_job.controller;

import com.project.it_job.dto.DegreeLevelDTO;
import com.project.it_job.request.DegreeLevelRequest;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.DegreeLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/degree-level")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class DegreeLevelController {

    private final DegreeLevelService degreeLevelService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(BaseResponse.success(degreeLevelService.getAll(), "OK"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return ResponseEntity.ok(BaseResponse.success(degreeLevelService.getById(id), "OK"));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DegreeLevelRequest dto) {
        return ResponseEntity.ok(BaseResponse.success(degreeLevelService.create(dto), "Tạo DegreeLevel thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody DegreeLevelRequest dto) {
        return ResponseEntity.ok(BaseResponse.success(degreeLevelService.update(id, dto), "Cập nhật DegreeLevel thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        degreeLevelService.delete(id);
        return ResponseEntity.ok(BaseResponse.success(null, "Xóa Degree có ID " + id + " thành công"));
    }
}
