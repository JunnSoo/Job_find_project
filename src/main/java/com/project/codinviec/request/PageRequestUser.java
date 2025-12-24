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
public class PageRequestUser {
    @Min(value = 1, message = "Số trang phải lớn hơn 0")
    private int pageNumber;

    @Min(value = 1, message = "Kích thước trang phải lớn hơn 0")
    private int pageSize;
    private String keyword;
    private String roleId;
    private Boolean block;
}
