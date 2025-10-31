package com.project.it_job.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveUpdateCategoryRequest {
    @NotNull(message = "name không được null")
    @NotEmpty(message = "name không được rỗng")
    private String name;

    @NotNull(message = "parentId không được null")
    private Integer parentId;
}
