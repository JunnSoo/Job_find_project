package com.project.it_job.service;

import com.project.it_job.dto.CertificateDto;
import com.project.it_job.request.CertificateRequest;
import com.project.it_job.request.PageRequestCustom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CertificateService {
    List<CertificateDto> getAllCertificate();
    Page<CertificateDto> getAllCertificatePage(PageRequestCustom pageRequestCustom);
    List<CertificateDto> getCertificateByUser(String userId);
    CertificateDto getCertificateById(Integer id);
    CertificateDto createCertificate(CertificateRequest request);
    CertificateDto updateCertificate(int id, CertificateRequest request);
    CertificateDto deleteCertificate(int id);
}
