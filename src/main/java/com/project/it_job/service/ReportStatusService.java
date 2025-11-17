package com.project.it_job.service;

import com.project.it_job.dto.ReportStatusDTO;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.ReportStatusRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReportStatusService {

    List<ReportStatusDTO> getAllStatus();
    Page<ReportStatusDTO> getAllStatusWithPage(PageRequestCustom req);
    ReportStatusDTO getStatusById(int id);
    ReportStatusDTO createStatus(ReportStatusRequest request);
    ReportStatusDTO updateStatus(int id, ReportStatusRequest request);
    void delete(int id);
}
