package com.project.it_job.request;

import lombok.Data;

@Data
public class JobRequest {
    private String jobPosition;
    private String companyId;
    private String detailAddress;
    private String descriptionJob;
    private String requirement;
    private String benefits;

    private int provinceId;
    private int industryId;
    private int jobLevelId;
    private int degreeLevelId;
    private int employmentTypeId;
    private int experienceId;

}
