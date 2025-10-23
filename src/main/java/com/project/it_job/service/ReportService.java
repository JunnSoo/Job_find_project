package com.project.it_job.service;

import com.project.it_job.entity.Report;

import java.util.List;
import java.util.Optional;

public interface ReportService {
    List<Report> getAllReports();
    Report getReportById(int id);
    Report createReport(Report report);
    Report updateReport(int id, Report reportDetails);
    boolean deleteReport(int id);
}
