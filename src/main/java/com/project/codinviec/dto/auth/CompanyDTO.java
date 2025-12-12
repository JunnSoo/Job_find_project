package com.project.codinviec.dto.auth;

import com.project.codinviec.dto.StatusSpecialDTO;
import com.project.codinviec.entity.StatusSpecialJob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyDTO {
    private String id;
    private String name;
    private String description;
    private String address;
    private String website;
    private String logo;
    private List<StatusSpecialDTO> statusSpecials;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

//Thêm sau
//    private int companyid;

}
