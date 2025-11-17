package com.project.it_job.request;

import jakarta.validation.constraints.Max;
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
public class SaveUpdateReviewRequest {

    @NotBlank(message = "title không được để trống")
    private String title;

    @NotBlank(message = "description không được để trống")
    private String description;

    @NotNull(message = "rated không được null")
    @Min(value = 1, message = "rated phải ít nhất là 1")
    @Max(value = 5, message = "rated không được lớn hơn 5")
    private int rated;

    @NotBlank(message = "userId không được để trống")
    private String userId;

    @NotBlank(message = "companyId không được để trống")
    private String companyId;
}
