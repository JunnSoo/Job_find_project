package com.project.it_job.mapper.auth;

import com.project.it_job.dto.auth.CompanyDTO;
import com.project.it_job.entity.CompanySize;
import com.project.it_job.entity.auth.Company;
import com.project.it_job.request.auth.SaveUpdateCompanyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CompanyMapper {

    public CompanyDTO companyToCompanyDTO(Company company){
        return CompanyDTO.builder()
                .id(company.getId())
                .name(company.getName())
                .description(company.getDescription())
                .logo(company.getLogo())
                .address(company.getAddress())
                .website(company.getWebsite())
                .createdDate(company.getCreatedDate())
                .updatedDate(company.getUpdatedDate())
                .build();
    }

    public Company saveCompanyMapper( CompanySize companySize,SaveUpdateCompanyRequest saveUpdateCompanyRequest){

        return Company.builder()
                .id(UUID.randomUUID().toString())
                .name(saveUpdateCompanyRequest.getName())
                .description(saveUpdateCompanyRequest.getDescription())
                .logo(saveUpdateCompanyRequest.getLogo())
                .address(saveUpdateCompanyRequest.getAddress())
                .website(saveUpdateCompanyRequest.getWebsite())
                .companySize(companySize)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
    }

    public Company updateCompanyMapper(String idCompany,CompanySize companySize ,SaveUpdateCompanyRequest saveUpdateCompanyRequest){
        return Company.builder()
                .id(idCompany)
                .name(saveUpdateCompanyRequest.getName())
                .description(saveUpdateCompanyRequest.getDescription())
                .logo(saveUpdateCompanyRequest.getLogo())
                .address(saveUpdateCompanyRequest.getAddress())
                .website(saveUpdateCompanyRequest.getWebsite())
                .companySize(companySize)
                .updatedDate(LocalDateTime.now())
                .build();
    }
}
