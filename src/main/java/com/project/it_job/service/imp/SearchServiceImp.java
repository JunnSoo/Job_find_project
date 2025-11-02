package com.project.it_job.service.imp;

import com.project.it_job.dto.JobDTO;
import com.project.it_job.dto.SearchDTO;
import com.project.it_job.dto.auth.CompanyDTO;
import com.project.it_job.entity.Job;
import com.project.it_job.entity.auth.Company;
import com.project.it_job.mapper.JobMapper;
import com.project.it_job.mapper.auth.CompanyMapper;
import com.project.it_job.repository.JobRepository;
import com.project.it_job.repository.auth.CompanyRepository;
import com.project.it_job.service.SearchService;
import com.project.it_job.specification.JobSpecification;
import com.project.it_job.specification.auth.CompanySpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImp implements SearchService {
    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;
    private final JobSpecification jobSpecification;
    private final CompanySpecification companySpecification;
    private final CompanyMapper companyMapper;

    @Override
    public SearchDTO getSearch(String keyword) {

        Specification<Job> specJob = Specification.allOf(jobSpecification.searchByName(keyword));
        Specification<Company> specCompany = Specification.allOf(companySpecification.searchByName(keyword));

        List<JobDTO> listJobDTO = jobRepository.findAll(specJob).stream().map( j -> JobMapper.toDTO(j)).toList();
        List<CompanyDTO> listCompanyDTO = companyRepository.findAll(specCompany).stream().map(c->companyMapper.companyToCompanyDTO(c)).toList();

        return SearchDTO.builder()
                .listJobDTO(listJobDTO)
                .listCompanyDTO(listCompanyDTO)
                .build();
    }
}
