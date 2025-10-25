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
public class UpdateBlogRequest {
    @NotNull(message = "id must be not null")
    private Integer id;
    @NotNull(message = "id must be not null")
    @NotEmpty(message = "id must be not empty")
    private String title;
    @NotNull(message = "picture must be not null")
    @NotEmpty(message = "picture must be not empty")
    private String picture;

    @NotNull(message = "shortDescription must be not null")
    private String shortDescription;

    @NotNull(message = "description must be not null")
    private String description;
}

