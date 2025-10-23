package com.project.it_job.controller;

import com.project.it_job.entity.Report;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "*")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getReportById(@PathVariable int id) {
        Report report = reportService.getReportById(id);
        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setMessage("Successfully report" );
        response.setData(report);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public Report createReport(@RequestBody Report report) {
        return reportService.createReport(report);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Report> updateReport(@PathVariable int id,@RequestBody Report report) {
        Report updated = reportService.updateReport(id, report);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteReport(@PathVariable int id) {
        boolean deleted = reportService.deleteReport(id);
        BaseResponse response = new BaseResponse();
        if(deleted) {
            response.setCode(200);
            response.setMessage("Successfully deleted report");
            response.setData(null);
            return ResponseEntity.ok(response);
        } else {
            response.setCode(404);
            response.setMessage("Report not found with id: " + id);
            response.setData(null);
            return ResponseEntity.status(404).body(response);
        }

    }
}
