package com.project.it_job.controller;

import com.project.it_job.dto.ReportStatusDTO;
import com.project.it_job.entity.ReportStatus;
import com.project.it_job.mapper.ReportStatusMapper;
import com.project.it_job.request.ReportStatusRequest;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.ReportStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/report-status")
@CrossOrigin(origins = "*")
public class ReportStatusController {

    @Autowired
    private ReportStatusService reportStatusService;

    @GetMapping
    public ResponseEntity<?> getAllStatus() {
        return ResponseEntity.ok(BaseResponse.success(reportStatusService.getAllStatus(), "OK"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStatusById(@PathVariable int id) {
        return ResponseEntity.ok(BaseResponse.success(reportStatusService.getStatusById(id), "OK"));
    }

    @PostMapping
    public ResponseEntity<?> createStatus(@RequestBody ReportStatusRequest request) {
        return ResponseEntity.ok(BaseResponse.success(reportStatusService.createStatus(request), "Tạo status thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable int id, @RequestBody ReportStatusRequest request) {
        return ResponseEntity.ok(BaseResponse.success(reportStatusService.updateStatus(id, request), "Cập nhật status thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStatus(@PathVariable int id) {
        reportStatusService.delete(id);
        return ResponseEntity.ok(BaseResponse.success(null, "Xóa status có ID " + id + " thành công"));
    }

}
