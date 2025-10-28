package com.project.it_job.service;

import com.project.it_job.dto.ReportDTO;
import com.project.it_job.request.ReportRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReportService {
    List<ReportDTO> getAllReports();
    ReportDTO getReportById(int id);
    ReportDTO createReport(ReportRequest request);
    ReportDTO updateReport(int id, ReportRequest request);
    void deleteReport(int id);
    Page<ReportDTO> getAllReportsPage(ReportRequest request);
}
