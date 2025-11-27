package com.project.codinviec.controller;

import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.ReportStatusRequest;
import com.project.codinviec.response.BaseResponse;
import com.project.codinviec.service.ReportStatusService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/report-status")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ReportStatusController {
    private final ReportStatusService reportStatusService;

    @GetMapping
    public ResponseEntity<?> getAllStatus(PageRequestCustom pageRequestCustom) {
        if (pageRequestCustom.getPageNumber() == 0 && pageRequestCustom.getPageSize() == 0 && pageRequestCustom.getKeyword() == null) {
            return ResponseEntity.ok(BaseResponse.success(reportStatusService.getAllStatus(), "OK"));
        }
        return ResponseEntity.ok(BaseResponse.success(reportStatusService.getAllStatusWithPage(pageRequestCustom), "OK"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStatusById(@PathVariable int id) {
        return ResponseEntity.ok(BaseResponse.success(reportStatusService.getStatusById(id), "OK"));
    }

    @PostMapping
    public ResponseEntity<?> createStatus(@Valid @RequestBody ReportStatusRequest request) {
        return ResponseEntity.ok(BaseResponse.success(reportStatusService.createStatus(request), "Tạo status thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable int id, @Valid @RequestBody ReportStatusRequest request) {
        return ResponseEntity.ok(BaseResponse.success(reportStatusService.updateStatus(id, request), "Cập nhật status thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStatus(@PathVariable int id) {
        reportStatusService.delete(id);
        return ResponseEntity.ok(BaseResponse.success(null, "Xóa status có ID " + id + " thành công"));
    }

}
