package com.project.it_job.controller;

import com.project.it_job.entity.JobLevel;
import com.project.it_job.request.JobLevelRequest;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/joblevel")
@CrossOrigin(origins = "*")
public class JoblevelController {
    @Autowired
    private JobLevelService jobLevelService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(BaseResponse.success(jobLevelService.getAll(), "OK"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return ResponseEntity.ok(BaseResponse.success(jobLevelService.getById(id), "OK"));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody JobLevelRequest request) {
        return ResponseEntity.ok(BaseResponse.success(jobLevelService.create(request), "Tạo JobLevel thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody JobLevelRequest request) {
        return ResponseEntity.ok(BaseResponse.success(jobLevelService.update(id, request), "Cập nhật Joblevel thành công"));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        jobLevelService.delete(id);
        return ResponseEntity.ok(BaseResponse.success(null, "Xóa Joblevel có ID " + id + " thành công"));
    }
}
