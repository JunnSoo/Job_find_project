package com.project.codinviec.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WishlistCandidateRequest {
    @NotBlank(message = "hrId không được để trống")
    private String hrId;

    @NotBlank(message = "candidateId không được để trống")
    private String candidateId;
}
