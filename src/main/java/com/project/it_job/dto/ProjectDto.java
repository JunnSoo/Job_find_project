package com.project.it_job.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDto {
    private int id;
    private String name;
    private String userId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String projectUrl;
    private String company;
}
