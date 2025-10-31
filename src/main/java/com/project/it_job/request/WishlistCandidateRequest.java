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
    @NotNull(message = "hrId không được null")
    @NotEmpty(message = "hrId không được rỗng")
    private String hrId;

    @NotNull(message = "candidateId không được null")
    @NotEmpty(message = "candidateId không được rỗng")
    private String candidateId;
}
