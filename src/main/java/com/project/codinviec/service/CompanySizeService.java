package com.project.codinviec.service;

import com.project.codinviec.dto.CompanySizeDTO;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.SaveUpdateCompanySizeRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CompanySizeService {
    List<CompanySizeDTO> getAllCompany();
    Page<CompanySizeDTO> getAllCompanyPage(PageRequestCustom pageRequestCustom);
    CompanySizeDTO getCompanyById(Integer id);
    CompanySizeDTO saveCompanySize(SaveUpdateCompanySizeRequest saveUpdateCompanySizeRequest);
    CompanySizeDTO updateCompanySize(Integer id,SaveUpdateCompanySizeRequest saveUpdateCompanySizeRequest);
    CompanySizeDTO deleteCompanySize(Integer id);
}
