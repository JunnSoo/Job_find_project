package com.project.it_job.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveUpdateReviewRequest {
    @NotNull(message = "title must be not null")
    @NotEmpty(message = "title must be not empty")
    private String title;
    @NotNull(message = "description must be not null")
    @NotEmpty(message = "description must be not empty")
    private String description;

    @NotNull(message = "rated must be not null")
    @Min(value = 1, message = "rated must be at least 1")
    @Max(value = 5, message = "rated must be less than or equal to 5")
    private int rated;

    @NotNull(message = "userId must be not null")
    @NotEmpty(message = "userId must be not empty")
    private String userId;

    @NotNull(message = "companyId must be not null")
    @NotEmpty(message = "companyId must be not empty")
    private String companyId;
}
