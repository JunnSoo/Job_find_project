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

    @NotNull(message = "title không được null")
    @NotEmpty(message = "title không được rỗng")
    private String title;

    @NotNull(message = "description không được null")
    @NotEmpty(message = "description không được rỗng")
    private String description;

    @NotNull(message = "rated không được null")
    @Min(value = 1, message = "rated phải ít nhất là 1")
    @Max(value = 5, message = "rated không được lớn hơn 5")
    private int rated;

    @NotNull(message = "userId không được null")
    @NotEmpty(message = "userId không được rỗng")
    private String userId;

    @NotNull(message = "companyId không được null")
    @NotEmpty(message = "companyId không được rỗng")
    private String companyId;
}
