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
                .company(companyMapper.companyToCompanyDTO(entity.getCompany()))
                .detailAddress(entity.getDetailAddress())
                .descriptionJob(entity.getDescriptionJob())
                .requirement(entity.getRequirement())
                .benefits(entity.getBenefits())
                .provinceId(entity.getProvince().getId())
                .provinceName(entity.getProvince().getName())
                .industryId(entity.getIndustry().getId())
                .industryName(entity.getIndustry().getName())
                .jobLevelId(entity.getJobLevel().getId())
                .jobLevelName(entity.getJobLevel().getName())
                .degreeLevelId(entity.getDegreeLevel().getId())
                .degreeLevelName(entity.getDegreeLevel().getName())
                .employmentTypeId(entity.getEmploymentType().getId())
                .employmentTypeName(entity.getEmploymentType().getName())
                .experienceId(entity.getExperience().getId())
                .experienceName(entity.getExperience().getName())
                .salary(entity.getSalary())
                .idJobStatus(entity.getJobStatus() != null ? entity.getJobStatus().getId() : null )
                .jobStatusName(entity.getJobStatus() != null ? entity.getJobStatus().getName() : null )
                .responsibility(entity.getResponsibility())
                .isAgreedSalary(entity.getIsAgreedSalary())
                .createdDate(entity.getCreatedDate())
                .updatedDate(entity.getUpdatedDate())
                .build();
    }


}
