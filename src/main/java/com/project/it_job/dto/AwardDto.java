package com.project.it_job.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AwardDto {
    private int id;
    private String userId;
    private String awardName;
    private String organization;
    private LocalDateTime date;
    private String description;
}
