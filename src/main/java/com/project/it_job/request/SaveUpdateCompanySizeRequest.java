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
public class SaveUpdateCompanySizeRequest {
    @NotNull(message = "minEmployees không được null")
    private int minEmployees;

    @NotNull(message = "maxEmployees không được null")
    private int maxEmployees;
}
