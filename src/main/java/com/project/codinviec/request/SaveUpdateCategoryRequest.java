package com.project.codinviec.request;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "name không được để trống")
    private String name;

    @NotNull(message = "parentId không được null")
    private Integer parentId;
}
