package com.project.it_job.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class IndustryRequest {
    @NotBlank(message = "Tên ngành nghề không được để trống")
    private String name;
}
