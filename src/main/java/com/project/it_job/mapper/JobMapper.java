package com.project.it_job.mapper;

import com.project.it_job.dto.JobDTO;
import com.project.it_job.entity.Job;
import com.project.it_job.request.JobRequest;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {
    public JobDTO toDTO(Job entity) {
        if (entity == null) return null;

        JobDTO dto = new JobDTO();
        dto.setId(entity.getId());
        dto.setJobPosition(entity.getJobPosition());
        dto.setCompanyId(entity.getCompanyId());
        dto.setDetailAddress(entity.getDetailAddress());
        dto.setDescriptionJob(entity.getDescriptionJob());
        dto.setRequirement(entity.getRequirement());
        dto.setBenefits(entity.getBenefits());
        dto.setProvinceId(entity.getProvinceId());
        dto.setIndustryId(entity.getIndustryId());
        dto.setJobLevelId(entity.getJobLevelId());
        dto.setDegreeLevelId(entity.getDegreeLevelId());
        dto.setEmploymentTypeId(entity.getEmploymentTypeId());
        dto.setExperienceId(entity.getExperienceId());

        return dto;
    }

    public Job toEntity(JobDTO dto) {
        if (dto == null) return null;

        Job entity = new Job();
        entity.setId(dto.getId());
        entity.setJobPosition(dto.getJobPosition());
        entity.setCompanyId(dto.getCompanyId());
        entity.setDetailAddress(dto.getDetailAddress());
        entity.setDescriptionJob(dto.getDescriptionJob());
        entity.setRequirement(dto.getRequirement());
        entity.setBenefits(dto.getBenefits());
        entity.setProvinceId(dto.getProvinceId());
        entity.setIndustryId(dto.getIndustryId());
        entity.setJobLevelId(dto.getJobLevelId());
        entity.setDegreeLevelId(dto.getDegreeLevelId());
        entity.setEmploymentTypeId(dto.getEmploymentTypeId());
        entity.setExperienceId(dto.getExperienceId());

        return entity;
    }

    public Job toEntity(JobRequest request) {
        if (request == null) return null;

        Job entity = new Job();
        entity.setJobPosition(request.getJobPosition());
        entity.setCompanyId(request.getCompanyId());
        entity.setDetailAddress(request.getDetailAddress());
        entity.setDescriptionJob(request.getDescriptionJob());
        entity.setRequirement(request.getRequirement());
        entity.setBenefits(request.getBenefits());
        entity.setProvinceId(request.getProvinceId());
        entity.setIndustryId(request.getIndustryId());
        entity.setJobLevelId(request.getJobLevelId());
        entity.setDegreeLevelId(request.getDegreeLevelId());
        entity.setEmploymentTypeId(request.getEmploymentTypeId());
        entity.setExperienceId(request.getExperienceId());
        return entity;
    }

    public void updateEntity(Job entity, JobRequest request) {
        if (request == null || entity == null) return;

        entity.setJobPosition(request.getJobPosition());
        entity.setCompanyId(request.getCompanyId());
        entity.setDetailAddress(request.getDetailAddress());
        entity.setDescriptionJob(request.getDescriptionJob());
        entity.setRequirement(request.getRequirement());
        entity.setBenefits(request.getBenefits());
        entity.setProvinceId(request.getProvinceId());
        entity.setIndustryId(request.getIndustryId());
        entity.setJobLevelId(request.getJobLevelId());
        entity.setDegreeLevelId(request.getDegreeLevelId());
        entity.setEmploymentTypeId(request.getEmploymentTypeId());
        entity.setExperienceId(request.getExperienceId());
    }

}
