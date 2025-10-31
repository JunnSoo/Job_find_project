package com.project.it_job.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserReviewDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String avatar;
}
