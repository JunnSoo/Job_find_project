package com.project.it_job.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogDTO {
    private int id;
    private String title;
    private String picture;
    private String shortDescription;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
