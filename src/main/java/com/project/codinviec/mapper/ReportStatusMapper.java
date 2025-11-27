package com.project.codinviec.mapper;

import com.project.codinviec.dto.ReportStatusDTO;
import com.project.codinviec.entity.ReportStatus;
import com.project.codinviec.request.ReportStatusRequest;
import org.springframework.stereotype.Component;

@Component
public class ReportStatusMapper {

    public ReportStatusDTO toDTO(ReportStatus entity) {
        if (entity == null) return null;
        return ReportStatusDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public ReportStatus saveReportStatus(ReportStatusRequest request) {
        if (request == null) return null;
        return ReportStatus.builder()
                .name(request.getName())
                .build();
    }

    public ReportStatus updateReportStatus(Integer id, ReportStatusRequest request) {
        if (request == null) return null;
        return ReportStatus.builder()
                .id(id)
                .name(request.getName())
                .build();
    }
}
