package com.project.codinviec.controller;

import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.ReportRequest;
import com.project.codinviec.response.BaseResponse;
import com.project.codinviec.service.ReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping
    public ResponseEntity<?> getAllReports(PageRequestCustom pageRequestCustom) {
        if (pageRequestCustom.getPageNumber() == 0 && pageRequestCustom.getPageSize() == 0 && pageRequestCustom.getKeyword() == null) {
            return ResponseEntity.ok(BaseResponse.success(reportService.getAllReports(), "OK"));
        }

        // Nếu có phân trang → trả kết quả theo trang
        return ResponseEntity.ok(BaseResponse.success(reportService.getAllReportsPage(pageRequestCustom),"OK"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReportById(@PathVariable int id) {
        return ResponseEntity.ok(BaseResponse.success(reportService.getReportById(id), "OK"));
    }

    @PostMapping
    public ResponseEntity<?> createReport(@Valid @RequestBody ReportRequest request) {
        return ResponseEntity.ok(BaseResponse.success(reportService.createReport(request), "Tạo report thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReport(@PathVariable int id, @Valid @RequestBody ReportRequest request) {
        return ResponseEntity.ok(BaseResponse.success(reportService.updateReport(id, request), "Cập nhật report thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReport(@PathVariable int id) {
        reportService.deleteReport(id);
        return ResponseEntity.ok(BaseResponse.success(null, "Xóa report có ID " + id + " thành công"));
    }
}
