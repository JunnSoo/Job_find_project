package com.project.it_job.request;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WishlistCandidateRequest {
    @NotNull(message = "hrId must be not null")
    @NotEmpty(message = "hrId must be not empty")
    private String hrId;

    @NotNull(message = "candidateId must be not null")
    @NotEmpty(message = "candidateId must be not empty")
    private String candidateId;
}
