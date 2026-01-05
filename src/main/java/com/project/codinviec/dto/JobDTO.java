package com.project.codinviec.dto;

import com.project.codinviec.dto.auth.CompanyDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobDTO {
    private int id;
    private String jobPosition;
    private CompanyDTO company;
    private String detailAddress;
    private String descriptionJob;
    private String requirement;
    private String benefits;
    private int provinceId;
    private String provinceName;
    private int industryId;
    private String industryName;
    private int jobLevelId;
    private String jobLevelName;
    private int degreeLevelId;
    private String degreeLevelName;
    private int employmentTypeId;
    private String employmentTypeName;
    private int experienceId;
    private String experienceName;
    private List<StatusSpecialDTO> statusSpecials;
    private List<AvailableSkillDTO> skills;
    private Boolean isAgreedSalary;
    private double salary;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
