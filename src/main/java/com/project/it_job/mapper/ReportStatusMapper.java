package com.project.it_job.mapper;

import com.project.it_job.dto.ReportStatusDTO;
import com.project.it_job.entity.ReportStatus;

public class ReportStatusMapper {

    public static ReportStatusDTO toDTO(ReportStatus entity) {
        if (entity == null) return null;

        ReportStatusDTO dto = new ReportStatusDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public static ReportStatus toEntity(ReportStatusDTO dto) {
        if (dto == null) return null;

        ReportStatus entity = new ReportStatus();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}
