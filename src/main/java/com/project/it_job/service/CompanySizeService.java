package com.project.it_job.service;

import com.project.it_job.dto.CompanySizeDTO;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.SaveUpdateCompanySizeRequest;
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
