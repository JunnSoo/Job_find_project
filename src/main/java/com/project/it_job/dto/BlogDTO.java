package com.project.it_job.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogDTO {
    private int id;
    private String title;
    private String picture;
    private String shortDescription;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
