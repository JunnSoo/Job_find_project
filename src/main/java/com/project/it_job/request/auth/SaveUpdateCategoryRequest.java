package com.project.it_job.request.auth;

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
    @NotNull(message = "name must be not null")
    @NotEmpty(message = "name must be not empty")
    private String name;

    @NotNull(message = "parent id must be not null")
    private Integer parentId;
}
