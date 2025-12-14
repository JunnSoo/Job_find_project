package com.project.codinviec.service;

import com.project.codinviec.dto.CompanyAddressDTO;
import com.project.codinviec.request.CompanyAddressRequest;
import com.project.codinviec.request.PageRequestCustom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CompanyAddressService {
    List<CompanyAddressDTO> getAllCompanyAddresses();
    Page<CompanyAddressDTO> getAllCompanyAddressesPage(PageRequestCustom pageRequestCustom);
    CompanyAddressDTO getCompanyAddressById(Integer id);
    CompanyAddressDTO saveCompanyAddress(CompanyAddressRequest companyAddressRequest);
    CompanyAddressDTO updateCompanyAddress(Integer id ,CompanyAddressRequest companyAddressRequest);
    CompanyAddressDTO deleteCompanyAddress(Integer id);
}
