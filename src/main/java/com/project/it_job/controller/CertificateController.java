package com.project.it_job.controller;

import com.project.it_job.request.CertificateRequest;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/certificate")
@RequiredArgsConstructor
public class CertificateController {

    private final CertificateService certificateService;

    @GetMapping
    public ResponseEntity<?> getAllCertificate(PageRequestCustom pageRequestCustom) {
        if (pageRequestCustom.getPageNumber() == 0
                && pageRequestCustom.getPageSize() == 0
                && pageRequestCustom.getKeyword() == null) {
            return ResponseEntity.ok(BaseResponse.success(certificateService.getAllCertificate(), "ok"));
        }
        return ResponseEntity.ok(BaseResponse.success(certificateService.getAllCertificatePage(pageRequestCustom), "ok"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCertificateById(@PathVariable Integer id) {
        return ResponseEntity.ok(BaseResponse.success(certificateService.getCertificateById(id), "ok"));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getCertificateByUser(@PathVariable String userId) {
        return ResponseEntity.ok(BaseResponse.success(certificateService.getCertificateByUser(userId), "ok"));
    }

    @PostMapping
    public ResponseEntity<?> createCertificate(@RequestBody CertificateRequest request) {
        return ResponseEntity.ok(BaseResponse.success(certificateService.createCertificate(request), "ok"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCertificate(@PathVariable Integer id,
                                               @RequestBody CertificateRequest request) {
        return ResponseEntity.ok(BaseResponse.success(certificateService.updateCertificate(id, request), "ok"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCertificate(@PathVariable Integer id) {
        return ResponseEntity.ok(BaseResponse.success(certificateService.deleteCertificate(id), "ok"));
    }
}
