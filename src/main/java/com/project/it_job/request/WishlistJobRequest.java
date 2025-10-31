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
public class WishlistJobRequest {
    @NotNull(message = "userId không được null")
    @NotEmpty(message = "userId không được rỗng")
    private String userId;

    @NotNull(message = "jobId không được null")
    private int jobId;
}
