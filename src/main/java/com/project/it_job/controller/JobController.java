package com.project.it_job.controller;

import com.project.it_job.dto.JobDTO;
import com.project.it_job.request.JobRequest;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;

    @GetMapping
    public ResponseEntity<?> getAllJob(PageRequestCustom pageRequestCustom) {
        if (pageRequestCustom.getPageNumber() == 0 && pageRequestCustom.getPageSize() == 0 && pageRequestCustom.getKeyword() == null) {
            return ResponseEntity.ok(BaseResponse.success(jobService.getAllJob(), "Lấy danh sách Job thành công"));
        }
        return ResponseEntity.ok(BaseResponse.success(jobService.getALLjobPage(pageRequestCustom), "Lấy danh sách Job phân trang thành công"));
    }

    @GetMapping("/{idJob}")
    public ResponseEntity<?> getJobById(@PathVariable("idJob") int idJob) {
        return ResponseEntity.ok(BaseResponse.success(jobService.getJobById(idJob), "Lấy Job thành công"));
    }

    @PostMapping
    public ResponseEntity<?> createJob(@Valid @RequestBody JobRequest request) {
        JobDTO createdJob = jobService.createJob(request);
        return ResponseEntity.ok(BaseResponse.success(createdJob, "Tạo Job thành công"));
    }
    @PutMapping("/{idJob}")
    public ResponseEntity<?> updateJob(@PathVariable("idJob") int idJob, @Valid @RequestBody JobRequest request) {
        JobDTO updatedJob = jobService.updateJob(idJob, request);
        return ResponseEntity.ok(BaseResponse.success(updatedJob, "Cập nhật Job thành công"));
    }

    @DeleteMapping("/{idJob}")
    public ResponseEntity<?> deleteJob(@PathVariable("idJob") int idJob) {
        jobService.deleteJob(idJob);
        return ResponseEntity.ok(BaseResponse.success("Đã xóa Job có ID: " + idJob, "Xóa Job thành công"));
    }
}

