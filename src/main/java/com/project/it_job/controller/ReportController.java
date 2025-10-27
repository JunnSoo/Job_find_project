package com.project.it_job.controller;

import com.project.it_job.dto.ReportDTO;
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

    //Lấy tất cả report
    @GetMapping
    public List<ReportDTO> getAllReports() {
        return reportService.getAllReports();
    }

    // Lấy report theo id
    @GetMapping("/{id}")
    public ReportDTO getReportById(@PathVariable int id) {
        return reportService.getReportById(id);
    }

    // Thêm mới report
    @PostMapping
    public ReportDTO createReport(@RequestBody ReportDTO dto) {
        return reportService.createReport(dto);
    }

    // Cập nhật report
    @PutMapping("/{id}")
    public ReportDTO updateReport(@PathVariable int id, @RequestBody ReportDTO dto) {
        return reportService.updateReport(id, dto);
    }

    // Xóa report
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReport(@PathVariable int id) {
        reportService.deleteReport(id);
        return ResponseEntity.ok("Xóa report thành công với ID: " + id);
    }
}
