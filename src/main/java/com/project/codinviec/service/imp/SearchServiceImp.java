package com.project.codinviec.service.imp;

import com.project.codinviec.dto.JobDTO;
import com.project.codinviec.dto.SearchDTO;
import com.project.codinviec.dto.auth.CompanyDTO;
import com.project.codinviec.entity.Job;
import com.project.codinviec.entity.auth.Company;
import com.project.codinviec.mapper.JobMapper;
import com.project.codinviec.mapper.auth.CompanyMapper;
import com.project.codinviec.repository.JobRepository;
import com.project.codinviec.repository.auth.CompanyRepository;
import com.project.codinviec.service.SearchService;
import com.project.codinviec.specification.JobSpecification;
import com.project.codinviec.specification.auth.CompanySpecification;
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
    private final JobMapper jobMapper;

    @Override
    public SearchDTO getSearch(String keyword, int provinceId) {

        Specification<Job> specJob = Specification.allOf(jobSpecification.searchByNameAndProvinceId(keyword, provinceId));
        Specification<Company> specCompany = Specification.allOf(companySpecification.searchByName(keyword));

        List<JobDTO> listJobDTO = jobRepository.findAll(specJob).stream().map(jobMapper::toDTO).toList();
        List<CompanyDTO> listCompanyDTO = companyRepository.findAll(specCompany).stream().map(companyMapper::companyToCompanyDTO).toList();



        return SearchDTO.builder()
                .listJobDTO(listJobDTO)
                .listCompanyDTO(listCompanyDTO)
                .build();
    }
}
