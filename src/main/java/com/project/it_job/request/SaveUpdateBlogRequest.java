package com.project.it_job.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveUpdateBlogRequest {
    @NotBlank(message = "title không được để trống")
    private String title;

    @NotBlank(message = "picture không được để trống")
    private String picture;

    @NotBlank(message = "shortDescription không được để trống")
    private String shortDescription;

    @NotBlank(message = "description không được để trống")
    private String description;
}
