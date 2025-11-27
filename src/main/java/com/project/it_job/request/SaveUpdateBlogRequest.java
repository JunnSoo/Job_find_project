package com.project.it_job.request;

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
public class SaveUpdateBlogRequest {
    @NotNull(message = "title không được để trống")
    @NotBlank(message = "title không được để trống")
    private String title;

    @NotNull(message = "picture không được để trống")
    @NotBlank(message = "picture không được để trống")
    private String picture;

    @NotNull(message = "shortDescription không được để trống")
    @NotBlank(message = "shortDescription không được để trống")
    private String shortDescription;

    @NotNull(message = "description không được để trống")
    @NotBlank(message = "description không được để trống")
    private String description;

    @NotNull(message = "isHighLight không được để trống")
    private Boolean isHighLight;}
