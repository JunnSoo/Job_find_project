package com.project.it_job.request;

import lombok.*;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AwardRequest {
    private String userId;
    private String awardName;
    private String organization;
    private LocalDateTime date;
    private String description;
}
