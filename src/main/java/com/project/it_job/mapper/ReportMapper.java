package com.project.it_job.mapper;

import com.project.it_job.dto.ReportDTO;
import com.project.it_job.entity.Report;
import com.project.it_job.entity.ReportStatus;

public class ReportMapper {
    public static ReportDTO toDTO(Report entity) {
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

    public static Report toEntity(ReportDTO dto, ReportStatus status) {
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
}
