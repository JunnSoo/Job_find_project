package com.project.codinviec.service.auth;

import com.project.codinviec.dto.auth.CompanyDTO;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.auth.SaveUpdateCompanyRequest;
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
