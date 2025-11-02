package com.project.it_job.dto;

import com.project.it_job.dto.auth.CompanyDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchDTO {
    private List<JobDTO> listJobDTO;
    private List<CompanyDTO> listCompanyDTO;
}
