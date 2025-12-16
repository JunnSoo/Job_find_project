package com.project.codinviec.dto.auth;

import com.project.codinviec.dto.CompanyAddressDTO;
import com.project.codinviec.dto.CompanySizeDTO;
import com.project.codinviec.dto.StatusSpecialDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyDTO {
    private String id;
    private String name;
    private String description;
    private String website;
    private String logo;
    private List<StatusSpecialDTO> statusSpecials;
    private CompanySizeDTO  companySize;
    private List<CompanyAddressDTO> companyAddress = new ArrayList<>();
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
