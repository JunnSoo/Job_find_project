package com.project.codinviec.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ApplyJobRequest {
    @NotNull(message = "userId không được null!")
    @NotEmpty(message = "userId không được rỗng!")
    private String userId;

    @NotNull(message = "idJob không được null!")
    private Integer idJob;
}
