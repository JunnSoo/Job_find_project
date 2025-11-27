package com.project.codinviec.service;

import com.project.codinviec.dto.ReportStatusDTO;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.ReportStatusRequest;
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
