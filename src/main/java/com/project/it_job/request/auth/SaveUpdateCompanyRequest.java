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
    @NotNull(message = "name không được null")
    @NotEmpty(message = "name không được rỗng")
    private String name;

    @NotNull(message = "description không được null")
    private String description;

    @NotNull(message = "address không được null")
    private String address;

    @NotNull(message = "website không được null")
    private String website;

    @NotNull(message = "logo không được null")
    private String logo;

    private Integer companySizeId;
}
