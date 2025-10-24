package com.project.it_job.service;

import com.project.it_job.entity.ReportStatus;

import java.util.List;
import java.util.Optional;

public interface ReportStatusService {

    List<ReportStatus> getAll();
    Optional<ReportStatus> getById(int id);
    ReportStatus create(ReportStatus reportStatus);
    ReportStatus update(int id,ReportStatus reportStatus);
    boolean delete(int id);
}
