package com.project.codinviec.mapper;

import com.project.codinviec.dto.CompanyAddressDTO;
import com.project.codinviec.entity.CompanyAddress;
import com.project.codinviec.entity.Province;
import com.project.codinviec.entity.Ward;
import com.project.codinviec.entity.auth.Company;
import com.project.codinviec.request.CompanyAddressRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyAddressMapper {
    private final ProvinceMapper provinceMapper;
    private final WardMapper wardMapper;

    public CompanyAddressDTO toCompanyAddressDTO(CompanyAddress companyAddress) {
        return CompanyAddressDTO.builder()
                .id(companyAddress.getId())
                .province(provinceMapper.toDTOCompany(companyAddress.getProvince()))
                .ward(wardMapper.toDTO(companyAddress.getWard()))
                .detail(companyAddress.getDetail())
                .isHeadOffice(companyAddress.getHeadOffice())
                .build();
    }


    public CompanyAddress saveCompanyMapper(CompanyAddressRequest companyAddressRequest, Province province, Ward ward) {
        return CompanyAddress
                .builder()
                .company(Company.builder().id(companyAddressRequest.getCompanyId()).build())
                .province(province)
                .ward(ward)
                .detail(companyAddressRequest.getDetail())
                .headOffice(companyAddressRequest.getIsHeadOffice())
                .build();
    }

    public CompanyAddress updateCompanyMapper(Integer id, CompanyAddressRequest companyAddressRequest, Province province, Ward ward) {
        return CompanyAddress
                .builder()
                .id(id)
                .company(Company.builder().id(companyAddressRequest.getCompanyId()).build())
                .province(province)
                .ward(ward)
                .detail(companyAddressRequest.getDetail())
                .headOffice(companyAddressRequest.getIsHeadOffice())
                .build();
    }
}
