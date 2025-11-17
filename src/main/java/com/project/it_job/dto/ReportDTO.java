package com.project.it_job.dto;

import lombok.Data;

@Data
public class ReportDTO {
    private int id;
    private String title;
    private String description;
    private String hinhAnh;
    private int statusId;
    private String createdReport;
    private String reportedUser;
    private int reportedJob;
}
