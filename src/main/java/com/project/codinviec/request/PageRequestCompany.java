package com.project.codinviec.request;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageRequestCompany {
    @Min(value = 1, message = "Số trang phải lớn hơn 0")
    private int pageNumber;

    @Min(value = 1, message = "Kích thước trang phải lớn hơn 0")
    private int pageSize;
    private String keyword;
    private Integer minEmployees;
    private Integer maxEmployees;
    private String location;
}
