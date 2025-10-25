package com.project.it_job.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveCategoryRequest {
    @NotNull(message = "name must be not null")
    @NotEmpty(message = "name must be not empty")
    private String name;

    @NotNull(message = "parent id must be not null")
    private Integer parentId;
}
