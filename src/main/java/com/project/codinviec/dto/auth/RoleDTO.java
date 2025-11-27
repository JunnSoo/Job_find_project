package com.project.codinviec.dto.auth;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RoleDTO {
    private String id;
    private String roleName;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
