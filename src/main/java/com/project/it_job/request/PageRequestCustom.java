package com.project.it_job.request;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class PageRequestCustom {
    @Min(value = 1, message = "Số trang phải lớn hơn 0")
    private int pageNumber;

    @Min(value = 1, message = "Kích thước trang phải lớn hơn 0")
    private int pageSize;

    private String sortBy;

    private String keyword;
}
