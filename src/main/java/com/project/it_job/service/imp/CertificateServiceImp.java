package com.project.it_job.service.imp;

import com.project.it_job.dto.CertificateDto;
import com.project.it_job.entity.Certificate;
import com.project.it_job.entity.auth.User;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.CertificateMapper;
import com.project.it_job.repository.CertificateRepository;
import com.project.it_job.repository.auth.UserRepository;
import com.project.it_job.request.CertificateRequest;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.service.CertificateService;
import com.project.it_job.util.PageCustomHelpper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateServiceImp implements CertificateService {

    private final CertificateRepository certificateRepository;
    private final UserRepository userRepository;
    private final CertificateMapper certificateMapper;
    private final PageCustomHelpper pageCustomHelpper;

    @Override
    public List<CertificateDto> getAllCertificate() {
        return certificateRepository.findAll()
                .stream()
                .map(certificateMapper::toDto)
                .toList();
    }

    @Override
    public Page<CertificateDto> getAllCertificatePage(PageRequestCustom pageRequestCustom) {
        PageRequestCustom validated = pageCustomHelpper.validatePageCustom(pageRequestCustom);
        Pageable pageable = PageRequest.of(validated.getPageNumber() - 1, validated.getPageSize());
        return certificateRepository.findAll(pageable).map(certificateMapper::toDto);
    }

    @Override
    public List<CertificateDto> getCertificateByUser(String userId) {
        return certificateRepository.findByUser_Id(userId)
                .stream()
                .map(certificateMapper::toDto)
                .toList();
    }

    @Override
    public CertificateDto getCertificateById(Integer id) {
        Certificate cert = certificateRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Khong tim thay id certificate"));
        return certificateMapper.toDto(cert);
    }

    @Override
    public CertificateDto createCertificate(CertificateRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Khong tim thay id user"));

        Certificate cert = Certificate.builder()
                .user(user)
                .certificateName(request.getCertificateName())
                .organization(request.getOrganization())
                .date(request.getDate())
                .link(request.getLink())
                .description(request.getDescription())
                .build();

        certificateRepository.save(cert);
        return certificateMapper.toDto(cert);
    }

    @Override
    public CertificateDto updateCertificate(int id, CertificateRequest request) {
        Certificate cert = certificateRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Khong tim thay id certificate"));

        cert.setCertificateName(request.getCertificateName() != null && !request.getCertificateName().isEmpty()
                ? request.getCertificateName()
                : cert.getCertificateName());

        cert.setOrganization(request.getOrganization() != null && !request.getOrganization().isEmpty()
                ? request.getOrganization()
                : cert.getOrganization());

        cert.setDate(request.getDate() != null ? request.getDate() : cert.getDate());
        cert.setLink(request.getLink() != null && !request.getLink().isEmpty() ? request.getLink() : cert.getLink());
        cert.setDescription(request.getDescription() != null && !request.getDescription().isEmpty()
                ? request.getDescription()
                : cert.getDescription());

        Certificate updated = certificateRepository.save(cert);
        return certificateMapper.toDto(updated);
    }

    @Override
    public CertificateDto deleteCertificate(int id) {
        Certificate cert = certificateRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Khong tim thay id certificate"));
        certificateRepository.delete(cert);
        return certificateMapper.toDto(cert);
    }
}
