package com.project.codinviec.mapper;

import com.project.codinviec.dto.JobDTO;
import com.project.codinviec.entity.Job;
import com.project.codinviec.request.JobRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class JobMapper {
    public JobDTO toDTO(Job entity) {
        if (entity == null) return null;
        return JobDTO.builder()
                .id(entity.getId())
                .jobPosition(entity.getJobPosition())
                .companyId(entity.getCompanyId())
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

    public Job saveJob(JobRequest request) {
        if (request == null) return null;
        return Job.builder()
                .jobPosition(request.getJobPosition())
                .companyId(request.getCompanyId())
                .detailAddress(request.getDetailAddress())
                .descriptionJob(request.getDescriptionJob())
                .requirement(request.getRequirement())
                .benefits(request.getBenefits())
                .provinceId(request.getProvinceId())
                .industryId(request.getIndustryId())
                .jobLevelId(request.getJobLevelId())
                .degreeLevelId(request.getDegreeLevelId())
                .employmentTypeId(request.getEmploymentTypeId())
                .experienceId(request.getExperienceId())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
    }

    public Job updateJob(int id, JobRequest request) {
        if (request == null) return null;
        return Job.builder()
                .id(id)
                .jobPosition(request.getJobPosition())
                .companyId(request.getCompanyId())
                .detailAddress(request.getDetailAddress())
                .descriptionJob(request.getDescriptionJob())
                .requirement(request.getRequirement())
                .benefits(request.getBenefits())
                .provinceId(request.getProvinceId())
                .industryId(request.getIndustryId())
                .jobLevelId(request.getJobLevelId())
                .degreeLevelId(request.getDegreeLevelId())
                .employmentTypeId(request.getEmploymentTypeId())
                .experienceId(request.getExperienceId())
                .createdDate(request.getCreatedDate())
                .updatedDate(LocalDateTime.now())
                .build();
    }
}
