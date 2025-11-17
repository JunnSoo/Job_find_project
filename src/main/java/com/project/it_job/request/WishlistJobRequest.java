package com.project.it_job.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "userId không được để trống")
    private String userId;

    @NotNull(message = "jobId không được null")
    @Min(value = 1, message = "jobId phải lớn hơn 0")
    private int jobId;
}
