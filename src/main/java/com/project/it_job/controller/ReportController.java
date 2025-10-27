package com.project.it_job.controller;

import com.project.it_job.dto.ReportDTO;
import com.project.it_job.request.ReportRequest;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping
    public ResponseEntity<?> getAllReports() {
        return ResponseEntity.ok(BaseResponse.success(reportService.getAllReports(), "OK"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReportById(@PathVariable int id) {
        return ResponseEntity.ok(BaseResponse.success(reportService.getReportById(id), "OK"));
    }

    @PostMapping
    public ResponseEntity<?> createReport(@RequestBody ReportRequest request) {
        return ResponseEntity.ok(BaseResponse.success(reportService.createReport(request), "Tạo report thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReport(@PathVariable int id, @RequestBody ReportRequest request) {
        return ResponseEntity.ok(BaseResponse.success(reportService.updateReport(id, request), "Cập nhật report thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReport(@PathVariable int id) {
        reportService.deleteReport(id);
        return ResponseEntity.ok(BaseResponse.success(null, "Xóa report có ID " + id + " thành công"));
    }
}
