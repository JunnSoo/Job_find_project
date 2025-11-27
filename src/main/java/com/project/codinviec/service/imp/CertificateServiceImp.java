package com.project.codinviec.service.imp;

import com.project.codinviec.dto.CertificateDTO;
import com.project.codinviec.entity.Certificate;
import com.project.codinviec.entity.auth.User;
import com.project.codinviec.exception.common.NotFoundIdExceptionHandler;
import com.project.codinviec.mapper.CertificateMapper;
import com.project.codinviec.repository.CertificateRepository;
import com.project.codinviec.repository.auth.UserRepository;
import com.project.codinviec.request.CertificateRequest;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.service.CertificateService;
import com.project.codinviec.util.helper.PageCustomHelper;
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
    private final PageCustomHelper pageCustomHelper;

    @Override
    public List<CertificateDTO> getAllCertificate() {
        return certificateRepository.findAll()
                .stream()
                .map(certificateMapper::toDto)
                .toList();
    }

    @Override
    public Page<CertificateDTO> getAllCertificatePage(PageRequestCustom pageRequestCustom) {
        PageRequestCustom validated = pageCustomHelper.validatePageCustom(pageRequestCustom);
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
