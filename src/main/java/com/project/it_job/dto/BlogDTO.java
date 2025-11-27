package com.project.it_job.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogDTO {
    private int id;
    private String title;
    private String picture;
    private String shortDescription;
    private boolean isHighLight;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
