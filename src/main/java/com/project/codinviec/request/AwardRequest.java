package com.project.codinviec.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AwardRequest {
    @NotBlank(message = "User ID không được để trống")
    private String userId;

    @NotBlank(message = "Tên giải thưởng không được để trống")
    private String awardName;

    @NotBlank(message = "Tổ chức trao không được để trống")
    private String organization;

    @NotNull(message = "Ngày nhận giải không được null")
    private LocalDateTime date;

    private String description;
}
