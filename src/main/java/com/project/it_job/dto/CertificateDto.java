package com.project.it_job.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CertificateDto {
    private int id;
    private String userId;
    private String certificateName;
    private String organization;
    private LocalDate date;
    private String link;
    private String description;
}
