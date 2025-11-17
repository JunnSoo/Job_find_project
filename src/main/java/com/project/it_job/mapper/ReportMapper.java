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

        return ReportDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .hinhAnh(entity.getHinhAnh())
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
