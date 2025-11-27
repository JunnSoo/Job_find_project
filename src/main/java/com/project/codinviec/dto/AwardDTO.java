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
public class AwardDTO {
    private int id;
    private String userId;
    private String awardName;
    private String organization;
    private LocalDateTime date;
    private String description;
}
