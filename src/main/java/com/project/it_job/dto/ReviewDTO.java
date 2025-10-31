package com.project.it_job.dto;

import com.project.it_job.dto.auth.UserDTO;
import com.project.it_job.dto.auth.UserReviewDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDTO {
    private int id;
    private String title;
    private String description;
    private int rated;
    private UserReviewDTO user;
}
