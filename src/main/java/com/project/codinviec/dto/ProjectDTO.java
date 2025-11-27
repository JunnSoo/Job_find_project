package com.project.codinviec.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDTO {
    private int id;
    private String name;
    private String userId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String projectUrl;
    private String company;
}
