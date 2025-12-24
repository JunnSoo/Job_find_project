package com.project.codinviec.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InforEmailSecurityDTO {
    private String email;
    private String firstName;
    private String password;
    private String dateCreated;
}
