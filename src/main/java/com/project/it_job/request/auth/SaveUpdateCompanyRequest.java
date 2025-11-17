package com.project.it_job.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveUpdateCompanyRequest {
    @NotBlank(message = "Tên công ty không được để trống")
    private String name;

    private String description;

    private String address;

    private String website;

    private String logo;

    private Integer companySizeId;
}
