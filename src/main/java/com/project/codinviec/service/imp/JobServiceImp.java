package com.project.codinviec.service.imp;

import com.project.codinviec.dto.JobDTO;
import com.project.codinviec.entity.*;
import com.project.codinviec.entity.auth.Company;
import com.project.codinviec.exception.common.NotFoundIdExceptionHandler;
import com.project.codinviec.mapper.AvailableSkillMapper;
import com.project.codinviec.mapper.JobMapper;
import com.project.codinviec.mapper.StatusSpecialMapper;
import com.project.codinviec.repository.AvailableSkillsJobRepository;
import com.project.codinviec.repository.JobRepository;
import com.project.codinviec.repository.StatusSpecialJobRepository;
import com.project.codinviec.repository.auth.CompanyRepository;
import com.project.codinviec.request.JobFilterRequest;
import com.project.codinviec.request.JobRequest;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.service.JobService;
import com.project.codinviec.specification.JobSpecification;
import com.project.codinviec.util.helper.PageCustomHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImp implements JobService {
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    private final CompanyRepository companyRepository;
    private final PageCustomHelper pageCustomHelper;
    private final JobSpecification jobSpecification;

    private final StatusSpecialJobRepository statusSpecialJobRepository;
    private final StatusSpecialMapper statusSpecialMapper;

    private final AvailableSkillsJobRepository availableSkillsJobRepository;
    private final AvailableSkillMapper availableSkillMapper;

    @Override
    public List<JobDTO> getAllJob() {
        List<JobDTO> jobDTOList = jobRepository.findAll().stream()
                .map(jobMapper::toDTO)
                .toList();

        for (JobDTO jobDTO : jobDTOList) {
            jobDTO.setStatusSpecials(statusSpecialMapper
                    .StatusSpecialJobToStatusSpecialDTO(statusSpecialJobRepository
                            .findByJob_Id(jobDTO.getId())));
            jobDTO.setSkills(availableSkillMapper
                    .AvailbleSkillJobToAvaibleSkill(
                            availableSkillsJobRepository.findByJob_Id(jobDTO.getId())));
        }
        return jobDTOList;
    }

    @Override
    public Page<JobDTO> getAllJobPage(PageRequestCustom pageRequestCustom) {
        PageRequestCustom pageRequestValidate = pageCustomHelper.validatePageCustom(pageRequestCustom);
//        Sort sort = switch (pageRequestValidate.getSortBy()) {
//            case "jobPositionAsc" -> Sort.by(Sort.Direction.ASC, "jobPosition");
//            case "jobPositionDesc" -> Sort.by(Sort.Direction.DESC, "jobPosition");
//
//            case "companyIdAsc" -> Sort.by(Sort.Direction.ASC, "companyId");
//            case "companyIdDesc" -> Sort.by(Sort.Direction.DESC, "companyId");
//
//            case "detailAddressAsc" -> Sort.by(Sort.Direction.ASC, "detailAddress");
//            case "detailAddressDesc" -> Sort.by(Sort.Direction.DESC, "detailAddress");
//
//            case "descriptionJobAsc" -> Sort.by(Sort.Direction.ASC, "descriptionJob");
//            case "descriptionJobDesc" -> Sort.by(Sort.Direction.DESC, "descriptionJob");
//
//            case "requirementsAsc" -> Sort.by(Sort.Direction.ASC, "requirement");
//            case "requirementsDesc" -> Sort.by(Sort.Direction.DESC, "requirement");
//
//            case "benefitsAsc" -> Sort.by(Sort.Direction.ASC, "benefits");
//            case "benefitsDesc" -> Sort.by(Sort.Direction.DESC, "benefits");
//
//            case "createdDateAsc" -> Sort.by(Sort.Direction.ASC, "createdDate");
//
//            case "updatedDateAsc" -> Sort.by(Sort.Direction.ASC, "updatedDate");
//            case "updatedDateDesc" -> Sort.by(Sort.Direction.DESC, "updatedDate");
//
//            default -> Sort.by(Sort.Direction.DESC, "createdDate");
//        };

        Pageable pageable = PageRequest.of(pageRequestValidate.getPageNumber() - 1, pageRequestValidate.getPageSize());

        Specification<Job> spec = Specification.allOf(jobSpecification.searchByName(pageRequestValidate.getKeyword()));

        return jobRepository.findAll(spec, pageable)
                .map((job) -> {
                    JobDTO jobDTO = jobMapper.toDTO(job);
                    jobDTO.setStatusSpecials(statusSpecialMapper
                            .StatusSpecialJobToStatusSpecialDTO(statusSpecialJobRepository
                                    .findByJob_Id(jobDTO.getId())));
                    jobDTO.setSkills(availableSkillMapper
                            .AvailbleSkillJobToAvaibleSkill(
                                    availableSkillsJobRepository.findByJob_Id(jobDTO.getId())));
                    return jobDTO;
                });
    }

    @Override
    public Page<JobDTO> getAllJobPageWithFilter(JobFilterRequest jobFilterRequest) {
        PageRequestCustom pageRequestValidate = pageCustomHelper.validatePageCustom(PageRequestCustom.builder()
                .pageSize(jobFilterRequest.getPageSize())
                .pageNumber(jobFilterRequest.getPageNumber())
                .keyword(jobFilterRequest.getKeyword())
                .build());
        Pageable pageable = PageRequest.of(pageRequestValidate.getPageNumber() - 1, pageRequestValidate.getPageSize());
        Specification<Job> spec = Specification.allOf(jobSpecification.filterJob(
                jobFilterRequest.getProvinceName(),
                jobFilterRequest.getIndustryNames(),
                jobFilterRequest.getJobLevelNames(),
                jobFilterRequest.getEmploymentTypeNames(),
                jobFilterRequest.getSalaryMin(),
                jobFilterRequest.getSalaryMax()
        ), jobSpecification.searchByName(jobFilterRequest.getKeyword().trim()));
        return jobRepository.findAll(spec, pageable)
                .map((job) -> {
                    JobDTO jobDTO = jobMapper.toDTO(job);
                    jobDTO.setStatusSpecials(statusSpecialMapper
                            .StatusSpecialJobToStatusSpecialDTO(statusSpecialJobRepository
                                    .findByJob_Id(jobDTO.getId())));
                    jobDTO.setSkills(availableSkillMapper
                            .AvailbleSkillJobToAvaibleSkill(
                                    availableSkillsJobRepository.findByJob_Id(jobDTO.getId())));
                    return jobDTO;
                });
    }

    @Override
    public JobDTO getJobById(int id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Job ID: " + id));
        JobDTO jobDTO = jobMapper.toDTO(job);
        jobDTO.setStatusSpecials(statusSpecialMapper
                    .StatusSpecialJobToStatusSpecialDTO(statusSpecialJobRepository
                            .findByJob_Id(job.getId())));
        jobDTO.setSkills(availableSkillMapper
                    .AvailbleSkillJobToAvaibleSkill(
                            availableSkillsJobRepository.findByJob_Id(job.getId())));
        return jobDTO;
    }

    @Override
    @Transactional
    public JobDTO createJob(JobRequest request) {
        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id Company"));

        Job job = Job.builder()
                .jobPosition(request.getJobPosition())
                .company(company)
                .detailAddress(request.getDetailAddress())
                .descriptionJob(request.getDescriptionJob())
                .requirement(request.getRequirement())
                .benefits(request.getBenefits())
                .province(Province.builder()
                        .id(request.getProvinceId()).build())
                .industry(Industry.builder()
                        .id(request.getIndustryId())
                        .build())
                .jobLevel(JobLevel.builder().id(request.getJobLevelId()).build())
                .degreeLevel(DegreeLevel.builder().id(request.getDegreeLevelId()).build())
                .employmentType(EmploymentType.builder().id(request.getEmploymentTypeId()).build())
                .experience(Experience.builder().id(request.getExperienceId()).build())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
        return jobMapper.toDTO(jobRepository.save(job));
    }

    @Override
    @Transactional
    public JobDTO updateJob(int id, JobRequest request) {
        jobRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Job ID: " + id));

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id Company"));

        Job job = Job.builder()
                .id(id)
                .jobPosition(request.getJobPosition())
                .company(company)
                .detailAddress(request.getDetailAddress())
                .descriptionJob(request.getDescriptionJob())
                .requirement(request.getRequirement())
                .benefits(request.getBenefits())
                .province(Province.builder()
                        .id(request.getProvinceId()).build())
                .industry(Industry.builder()
                        .id(request.getIndustryId())
                        .build())
                .jobLevel(JobLevel.builder().id(request.getJobLevelId()).build())
                .degreeLevel(DegreeLevel.builder().id(request.getDegreeLevelId()).build())
                .employmentType(EmploymentType.builder().id(request.getEmploymentTypeId()).build())
                .experience(Experience.builder().id(request.getExperienceId()).build())
                .createdDate(request.getCreatedDate())
                .updatedDate(LocalDateTime.now())
                .build();
        return jobMapper.toDTO(jobRepository.save(job));
    }

    @Override
    @Transactional
    public void deleteJob(int id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Job ID: " + id));
        jobRepository.delete(job);
    }


}
