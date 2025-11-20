package com.project.it_job.service.imp;

import com.project.it_job.dto.ReportDTO;
import com.project.it_job.entity.Report;
import com.project.it_job.entity.ReportStatus;
import com.project.it_job.exception.common.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.ReportMapper;
import com.project.it_job.repository.ReportRepository;
import com.project.it_job.repository.ReportStatusRepository;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.ReportRequest;
import com.project.it_job.service.ReportService;
import com.project.it_job.util.helper.PageCustomHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImp implements ReportService {
    private final ReportRepository reportRepository;
    private final ReportStatusRepository reportStatusRepository;
    private final PageCustomHelper pageCustomHelper;
    private final ReportMapper reportMapper;

    @Override
    public List<ReportDTO> getAllReports() {
        return reportRepository.findAll()
                .stream()
                .map(reportMapper::toDTO)
                .toList();
    }

    @Override
    public ReportDTO getReportById(int id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Report với ID: " + id));
        return reportMapper.toDTO(report);
    }

    @Override
    @Transactional
    public ReportDTO createReport(ReportRequest request) {
        ReportStatus status = reportStatusRepository.findById(request.getStatusId())
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy ReportStatus với ID: " + request.getStatusId()));

        Report entity = reportMapper.saveReport(request, status);
        return reportMapper.toDTO(reportRepository.save(entity));
    }

    @Override
    @Transactional
    public ReportDTO updateReport(int id, ReportRequest request) {
        reportRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Report với ID: " + id));

        ReportStatus status = reportStatusRepository.findById(request.getStatusId())
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy ReportStatus với ID: " + request.getStatusId()));

        Report entity = reportMapper.updateReport(id, request, status);
        return reportMapper.toDTO(reportRepository.save(entity));
    }

    @Override
    @Transactional
    public void deleteReport(int id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Report với ID: " + id));
        reportRepository.delete(report);
    }
    @Override
    public Page<ReportDTO> getAllReportsPage(PageRequestCustom pageRequestCustom) {
        PageRequestCustom pageRequestValidate = pageCustomHelper.validatePageCustom(pageRequestCustom);
        Pageable pageable = PageRequest.of(pageRequestValidate.getPageNumber() - 1, pageRequestValidate.getPageSize());
        return reportRepository.findAll(pageable).map(reportMapper::toDTO);
    }
}
