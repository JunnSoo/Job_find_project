package com.project.it_job.request;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequest {
    private String userId;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String projectUrl;
    private String company;
}
