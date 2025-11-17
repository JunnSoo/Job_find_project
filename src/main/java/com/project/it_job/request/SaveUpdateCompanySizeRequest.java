package com.project.it_job.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveUpdateCompanySizeRequest {
    @NotNull(message = "minEmployees không được null")
    @Min(value = 0, message = "minEmployees phải lớn hơn hoặc bằng 0")
    private int minEmployees;

    @NotNull(message = "maxEmployees không được null")
    @Min(value = 1, message = "maxEmployees phải lớn hơn 0")
    private int maxEmployees;
}
