package com.project.it_job.mapper;

import com.project.it_job.dto.CertificateDto;
import com.project.it_job.entity.Certificate;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
@Builder
public class CertificateMapper {

    public CertificateDto toDto(Certificate certificate) {
        if (certificate == null) return null;
        return CertificateDto.builder()
                .id(certificate.getId())
                .userId(certificate.getUser() != null ? certificate.getUser().getId() : null)
                .certificateName(certificate.getCertificateName())
                .organization(certificate.getOrganization())
                .date(certificate.getDate())
                .link(certificate.getLink())
                .description(certificate.getDescription())
                .build();
    }
}
