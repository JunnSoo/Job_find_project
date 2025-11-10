package com.project.it_job.request;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;



@Data
public class ProvinceRequest {

    @NotEmpty(message = "Tên không được để trống")
    private String name;

    @NotNull(message = "Ward không được để trống")
    private Integer wardId;
}
