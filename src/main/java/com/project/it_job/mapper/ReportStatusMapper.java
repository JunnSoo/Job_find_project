package com.project.it_job.mapper;

import com.project.it_job.dto.ReportStatusDTO;
import com.project.it_job.entity.ReportStatus;
import com.project.it_job.request.ReportStatusRequest;
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

    public ReportStatus toEntity(ReportStatusDTO dto) {
        if (dto == null) return null;
        return ReportStatus.builder()
                .id(dto.getId())
                .name(dto.getName())
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
