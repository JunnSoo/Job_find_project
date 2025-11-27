package com.project.codinviec.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.codinviec.dto.auth.UserReviewDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewDTO {
    private int id;
    private String title;
    private String description;
    private int rated;
    private UserReviewDTO user;
}
