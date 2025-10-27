package com.project.it_job.service;

import com.project.it_job.dto.ReportStatusDTO;
import com.project.it_job.entity.ReportStatus;
import com.project.it_job.request.ReportStatusRequest;

import java.util.List;
import java.util.Optional;

public interface ReportStatusService {

    List<ReportStatusDTO> getAllStatus();
    ReportStatusDTO getStatusById(int id);
    ReportStatusDTO createStatus(ReportStatusRequest request);
    ReportStatusDTO updateStatus(int id, ReportStatusRequest request);
    void delete(int id);
}
