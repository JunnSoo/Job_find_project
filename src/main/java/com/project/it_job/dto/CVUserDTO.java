package com.project.it_job.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class CVUserDTO {
    private Integer id;
    private String candidateId;
    private Integer version;
    private String fileUrl;
    private String title;
    private LocalDateTime createdAt;
    private boolean isActive;

}
