package com.project.it_job.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveUpdateCompanySizeRequest {
    private int minEmployees;
    private int maxEmployees;
}
