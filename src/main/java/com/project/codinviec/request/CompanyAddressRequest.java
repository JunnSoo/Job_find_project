package com.project.codinviec.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CompanyAddressRequest {
    @NotNull(message = "companyId không được để trống")
    @NotBlank(message = "companyId không được để trống")
    private String companyId;

    @NotNull(message = "provinceId không được để trống")
    @NotBlank(message = "provinceId không được để trống")
    private Integer provinceId;

    @NotNull(message = "wardId không được để trống")
    @NotBlank(message = "wardId không được để trống")
    private Integer wardId;

    @NotNull(message = "detail không được để trống")
    private String detail;

    @NotNull(message = "isHeadOffice không được để trống")
    private Boolean isHeadOffice;
}
