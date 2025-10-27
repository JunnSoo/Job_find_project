package com.project.it_job.service;

import com.project.it_job.dto.ReportDTO;

import java.util.List;

public interface ReportService {
    List<ReportDTO> getAllReports();
    ReportDTO getReportById(int id);
    ReportDTO createReport(ReportDTO dto);
    ReportDTO updateReport(int id, ReportDTO dto);
    void deleteReport(int id);
}
