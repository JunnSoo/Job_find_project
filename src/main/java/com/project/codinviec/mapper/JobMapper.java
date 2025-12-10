package com.project.codinviec.mapper;

import com.project.codinviec.dto.JobDTO;
import com.project.codinviec.entity.Job;
import com.project.codinviec.mapper.auth.CompanyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class JobMapper {
    private final CompanyMapper companyMapper;

    public JobDTO toDTO(Job entity) {
        if (entity == null) return null;
        return JobDTO.builder()
                .id(entity.getId())
                .jobPosition(entity.getJobPosition())
                .companies(companyMapper.companyToCompanyDTO(entity.getCompany()))
                .detailAddress(entity.getDetailAddress())
                .descriptionJob(entity.getDescriptionJob())
                .requirement(entity.getRequirement())
                .benefits(entity.getBenefits())
                .provinceId(entity.getProvinceId())
                .industryId(entity.getIndustryId())
                .jobLevelId(entity.getJobLevelId())
                .degreeLevelId(entity.getDegreeLevelId())
                .employmentTypeId(entity.getEmploymentTypeId())
                .experienceId(entity.getExperienceId())
                .createdDate(entity.getCreatedDate())
                .updatedDate(entity.getUpdatedDate())
                .build();
    }


}
