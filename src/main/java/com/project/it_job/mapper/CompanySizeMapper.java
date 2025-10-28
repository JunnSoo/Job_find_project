package com.project.it_job.mapper;

import com.project.it_job.dto.CompanySizeDTO;
import com.project.it_job.entity.CompanySize;
import com.project.it_job.request.SaveUpdateCompanySizeRequest;
import org.springframework.stereotype.Component;

@Component
public class CompanySizeMapper {
    public CompanySizeDTO companySizeToCompanySizeDTO(CompanySize companySize) {
        return CompanySizeDTO.builder()
                .id(companySize.getId())
                .minEmployees(companySize.getMinEmployees())
                .maxEmployees(companySize.getMaxEmployees())
                .build();
    }

    public CompanySize saveCompanySizeMapper(SaveUpdateCompanySizeRequest request) {
        return CompanySize.builder()
                .minEmployees(request.getMinEmployees())
                .maxEmployees(request.getMaxEmployees())
                .build();
    }

    public CompanySize updateCompanySizeMapper(Integer idCompanySize, SaveUpdateCompanySizeRequest request) {
        return CompanySize.builder()
                .id(idCompanySize)
                .minEmployees(request.getMinEmployees())
                .maxEmployees(request.getMaxEmployees())
                .build();
    }

}
