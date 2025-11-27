package com.project.codinviec.dto;

import com.project.codinviec.dto.auth.CompanyDTO;
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
