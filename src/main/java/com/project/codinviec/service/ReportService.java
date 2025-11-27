package com.project.codinviec.service;

import com.project.codinviec.dto.ReportDTO;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.ReportRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReportService {
    List<ReportDTO> getAllReports();

    ReportDTO getReportById(int id);
    ReportDTO createReport(ReportRequest request);
    ReportDTO updateReport(int id, ReportRequest request);
    void deleteReport(int id);
    Page<ReportDTO> getAllReportsPage(PageRequestCustom request);
}
