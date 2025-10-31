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
public class SaveUpdateBlogRequest {
    @NotNull(message = "title không được null")
    @NotEmpty(message = "title không được rỗng")
    private String title;
    @NotNull(message = "picture không được null")
    @NotEmpty(message = "picture không được rỗng")
    private String picture;

    @NotNull(message = "shortDescription không được null")
    private String shortDescription;

    @NotNull(message = "description không được null")
    private String description;
}
