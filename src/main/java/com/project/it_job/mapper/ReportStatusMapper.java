package com.project.it_job.mapper;

import com.project.it_job.dto.ReportStatusDTO;
import com.project.it_job.entity.ReportStatus;

public class ReportStatusMapper {

    public static ReportStatusDTO toDTO(ReportStatus reportStatusEntity) {
        if(reportStatusEntity == null) return null;
        return new ReportStatusDTO(reportStatusEntity.getId(), reportStatusEntity.getName());
    }

    public static ReportStatus toEntity(ReportStatusDTO reportStatusDTO) {
        if(reportStatusDTO == null) return null;
        ReportStatus reportStatusEntity = new ReportStatus();
        reportStatusEntity.setId(reportStatusDTO.getId());
        reportStatusEntity.setName(reportStatusDTO.getName());
        return reportStatusEntity;
    }
}
