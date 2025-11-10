package com.project.it_job.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WardRequest {
    @NotEmpty(message = "Tên không được để trống")
    private String name;

    @NotNull(message = "Province không được để trống")
    private Integer idProvince;
}
