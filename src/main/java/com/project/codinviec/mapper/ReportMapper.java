package com.project.codinviec.mapper;

import com.project.codinviec.dto.ReportDTO;
import com.project.codinviec.entity.Report;
import com.project.codinviec.entity.ReportStatus;
import com.project.codinviec.request.ReportRequest;
import org.springframework.stereotype.Component;

@Component
public class ReportMapper {
    public ReportDTO toDTO(Report entity) {
        if (entity == null) return null;

        return ReportDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .imageUrl(entity.getImageUrl())
                .statusId(entity.getStatusId() != null ? entity.getStatusId().getId() : 0)
                .createdReport(entity.getCreatedReport())
                .reportedUser(entity.getReportedUser())
                .reportedJob(entity.getReportedJob() != null ? entity.getReportedJob() : 0)
                .build();
    }

    public Report saveReport(ReportRequest request, ReportStatus status) {
        if (request == null) return null;
        return Report.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .imageUrl(request.getImageUrl())
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
                .imageUrl(request.getImageUrl())
                .createdReport(request.getCreatedReport())
                .reportedUser(request.getReportedUser())
                .reportedJob(request.getReportedJob())
                .statusId(status)
                .build();
    }
}
