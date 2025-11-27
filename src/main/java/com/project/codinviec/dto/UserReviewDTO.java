package com.project.codinviec.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserReviewDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String avatar;
    private List<ReviewDTO> listReviews;
}
