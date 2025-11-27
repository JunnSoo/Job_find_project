package com.project.codinviec.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobDTO {
    private int id;
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
