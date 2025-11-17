package com.project.it_job.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class WardRequest {
    @NotBlank(message = "Tên phường/xã không được để trống")
    private String name;
    
    @NotNull(message = "ID tỉnh/thành phố không được để trống")
    @Positive(message = "ID tỉnh/thành phố phải là số dương")
    private Integer idProvince;
}

