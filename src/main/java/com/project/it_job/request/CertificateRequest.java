package com.project.it_job.request;

import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CertificateRequest {
    private String userId;
    private String certificateName;
    private String organization;
    private LocalDate date;
    private String link;
    private String description;
}
