package com.project.it_job.request.auth;

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
public class SaveUpdateCompanyRequest {
    @NotNull(message = "name must be not null")
    @NotEmpty(message = "name must be not empty")
    private String name;

    @NotNull(message = "description must be not null")
    private String description;

    @NotNull(message = "address must be not null")
    private String address;

    @NotNull(message = "website must be not null")
    private String website;

    @NotNull(message = "logo must be not null")
    private String logo;

    private Integer companySizeId;
}
