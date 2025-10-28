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
    @NotNull(message = "title must be not null")
    @NotEmpty(message = "title must be not empty")
    private String title;
    @NotNull(message = "picture must be not null")
    @NotEmpty(message = "picture must be not empty")
    private String picture;

    @NotNull(message = "shortDescription must be not null")
    private String shortDescription;

    @NotNull(message = "description must be not null")
    private String description;
}
