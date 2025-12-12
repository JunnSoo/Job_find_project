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
    private CompanyDTO companies;
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
    private List<StatusSpecialDTO> statusSpecials;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
