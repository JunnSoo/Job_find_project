package com.project.it_job.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ReportRequest {
    private String title;
    private String description;
    private String hinhAnh;
    private int statusId;
    private String createdReport;
    private String reportedUser;
    private int reportedJob;

}
