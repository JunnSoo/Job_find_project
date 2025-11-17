package com.project.it_job.mapper;

import com.project.it_job.dto.ReportDTO;
import com.project.it_job.entity.Report;
import com.project.it_job.entity.ReportStatus;
import com.project.it_job.request.ReportRequest;
import org.springframework.stereotype.Component;

@Component
public class ReportMapper {
    public ReportDTO toDTO(Report entity) {
        if (entity == null) return null;

        ReportDTO dto = new ReportDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setHinhAnh(entity.getHinhAnh());
        dto.setStatusId(entity.getStatusId() != null ? entity.getStatusId().getId() : 0);
        dto.setCreatedReport(entity.getCreatedReport());
        dto.setReportedUser(entity.getReportedUser());
        dto.setReportedJob(entity.getReportedJob() != null ? entity.getReportedJob() : 0);

        return dto;
    }

    public Report toEntity(ReportDTO dto, ReportStatus status) {
        if (dto == null) return null;

        Report entity = new Report();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setHinhAnh(dto.getHinhAnh());
        entity.setStatusId(status);
        entity.setCreatedReport(dto.getCreatedReport());
        entity.setReportedUser(dto.getReportedUser());
        entity.setReportedJob(dto.getReportedJob());

        return entity;
    }

    public Report saveReport(ReportRequest request, ReportStatus status) {
        if (request == null) return null;
        return Report.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .hinhAnh(request.getHinhAnh())
                .createdReport(request.getCreatedReport())
                .reportedUser(request.getReportedUser())
                .reportedJob(request.getReportedJob())
                .statusId(status)
                .build();
    }

    public Report updateReport(Integer id, ReportRequest request, ReportStatus status) {
        if (request == null) return null;
        return Report.builder()
                .id(id)
                .title(request.getTitle())
                .description(request.getDescription())
                .hinhAnh(request.getHinhAnh())
                .createdReport(request.getCreatedReport())
                .reportedUser(request.getReportedUser())
                .reportedJob(request.getReportedJob())
                .statusId(status)
                .build();
    }
}
