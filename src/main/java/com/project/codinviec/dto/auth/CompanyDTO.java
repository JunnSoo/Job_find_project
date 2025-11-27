package com.project.codinviec.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

//Thêm sau
//    private int companyid;

}
