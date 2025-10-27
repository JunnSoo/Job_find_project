package com.project.it_job.controller;

import com.project.it_job.dto.ReportStatusDTO;
import com.project.it_job.entity.ReportStatus;
import com.project.it_job.mapper.ReportStatusMapper;
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

    //Lấy danh sách tất cả status
    @GetMapping
    public List<ReportStatusDTO> getAllStatuses() {
        return reportStatusService.getAll();
    }

    //Lấy 1 status theo ID
    @GetMapping("/{id}")
    public ReportStatusDTO getStatusById(@PathVariable int id) {
        return reportStatusService.getById(id);
    }

    // Thêm mới status
    @PostMapping
    public ReportStatusDTO createStatus(@RequestBody ReportStatusDTO dto) {
        return reportStatusService.create(dto);
    }

    // Cập nhật status
    @PutMapping("/{id}")
    public ReportStatusDTO updateStatus(@PathVariable int id, @RequestBody ReportStatusDTO dto) {
        return reportStatusService.update(id, dto);
    }

    // Xóa status
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStatus(@PathVariable int id) {
        reportStatusService.delete(id);
        return ResponseEntity.ok("Xóa trạng thái report thành công với ID: " + id);
    }

}
