package com.project.it_job.service.imp;

import com.project.it_job.dto.CertificateDTO;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateServiceImp implements CertificateService {

    private final CertificateRepository certificateRepository;
    private final UserRepository userRepository;
    private final CertificateMapper certificateMapper;
    private final PageCustomHelpper pageCustomHelpper;

    @Override
    public List<CertificateDTO> getAllCertificate() {
        return certificateRepository.findAll()
                .stream()
                .map(certificateMapper::toDto)
                .toList();
    }

    @Override
    public Page<CertificateDTO> getAllCertificatePage(PageRequestCustom pageRequestCustom) {
        PageRequestCustom validated = pageCustomHelpper.validatePageCustom(pageRequestCustom);
        Pageable pageable = PageRequest.of(validated.getPageNumber() - 1, validated.getPageSize());
        return certificateRepository.findAll(pageable).map(certificateMapper::toDto);
    }

    @Override
    public List<CertificateDTO> getCertificateByUser(String userId) {
        return certificateRepository.findByUser_Id(userId)
                .stream()
                .map(certificateMapper::toDto)
                .toList();
    }

    @Override
    public CertificateDTO getCertificateById(Integer id) {
        Certificate cert = certificateRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id certificate"));
        return certificateMapper.toDto(cert);
    }

    @Override
    @Transactional
    public CertificateDTO createCertificate(CertificateRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id user"));

        Certificate cert = certificateMapper.saveCertificate(user, request);
        certificateRepository.save(cert);
        return certificateMapper.toDto(cert);
    }

    @Override
    @Transactional
    public CertificateDTO updateCertificate(int id, CertificateRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id user"));
        certificateRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id certificate"));

        Certificate cert = certificateMapper.updateCertificate(id, user, request);
        Certificate updated = certificateRepository.save(cert);
        return certificateMapper.toDto(updated);
    }

    @Override
    @Transactional
    public CertificateDTO deleteCertificate(int id) {
        Certificate cert = certificateRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id certificate"));
        certificateRepository.delete(cert);
        return certificateMapper.toDto(cert);
    }
}
