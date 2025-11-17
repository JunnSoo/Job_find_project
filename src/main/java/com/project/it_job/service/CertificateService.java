package com.project.it_job.service;

import com.project.it_job.dto.CertificateDTO;
import com.project.it_job.request.CertificateRequest;
import com.project.it_job.request.PageRequestCustom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CertificateService {
    List<CertificateDTO> getAllCertificate();
    Page<CertificateDTO> getAllCertificatePage(PageRequestCustom pageRequestCustom);
    List<CertificateDTO> getCertificateByUser(String userId);
    CertificateDTO getCertificateById(Integer id);
    CertificateDTO createCertificate(CertificateRequest request);
    CertificateDTO updateCertificate(int id, CertificateRequest request);
    CertificateDTO deleteCertificate(int id);
}
