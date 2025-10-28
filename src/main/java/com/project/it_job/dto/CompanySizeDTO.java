package com.project.it_job.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanySizeDTO {
    private int id;
    private int minEmployees;
    private int maxEmployees;
}
