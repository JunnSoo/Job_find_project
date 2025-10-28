package com.project.it_job.service.auth;

import com.project.it_job.dto.auth.CompanyDTO;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.auth.SaveUpdateCompanyRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CompanyService {
    List<CompanyDTO> getAllCompany();
    Page<CompanyDTO> getAllCompanyPage(PageRequestCustom pageRequestCustom);
    CompanyDTO  getCompanyById(String idCompany);
    CompanyDTO saveCompany(SaveUpdateCompanyRequest saveUpdateCompanyRequest);
    CompanyDTO updateCompany(String idCompany ,SaveUpdateCompanyRequest saveUpdateCompanyRequest);
    CompanyDTO deleteCompany(String idCompany);
}
