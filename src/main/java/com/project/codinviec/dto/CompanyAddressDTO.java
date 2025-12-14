package com.project.codinviec.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyAddressDTO {

    private int id;
    private ProvinceCompanyDTO province;
    private WardDTO ward;
    private String detail;
    private boolean isHeadOffice;
}
