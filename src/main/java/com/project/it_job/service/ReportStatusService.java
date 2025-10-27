package com.project.it_job.service;

import com.project.it_job.dto.ReportStatusDTO;
import com.project.it_job.entity.ReportStatus;

import java.util.List;
import java.util.Optional;

public interface ReportStatusService {

    List<ReportStatusDTO> getAll();
    ReportStatusDTO getById(int id);
    ReportStatusDTO create(ReportStatusDTO dto);
    ReportStatusDTO update(int id, ReportStatusDTO dto);
    void delete(int id);
}
