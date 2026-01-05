package com.project.codinviec.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class JobFilterRequest {

    private String provinceName;

    private List<String> industryNames;

    private List<String> jobLevelNames;

    private List<String> employmentTypeNames;

    private Double salaryMin;

    private Double salaryMax;

    @Min(value = 1, message = "Số trang phải lớn hơn 0")
    private int pageNumber;

    @Min(value = 1, message = "Kích thước trang phải lớn hơn 0")
    private int pageSize;

    private String keyword;
}
