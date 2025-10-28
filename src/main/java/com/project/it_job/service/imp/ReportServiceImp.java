package com.project.it_job.service.imp;

import com.project.it_job.dto.ReportDTO;
import com.project.it_job.entity.Report;
import com.project.it_job.entity.ReportStatus;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.ReportMapper;
import com.project.it_job.repository.ReportRepository;
import com.project.it_job.repository.ReportStatusRepository;
import com.project.it_job.request.ReportRequest;
import com.project.it_job.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportServiceImp implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ReportStatusRepository reportStatusRepository;

    @Override
    public List<ReportDTO> getAllReports() {
        return reportRepository.findAll()
                .stream()
                .map(ReportMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReportDTO getReportById(int id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Report với ID: " + id));
        return ReportMapper.toDTO(report);
    }

    @Override
    public ReportDTO createReport(ReportRequest request) {
        ReportStatus status = reportStatusRepository.findById(request.getStatusId())
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy ReportStatus với ID: " + request.getStatusId()));

        Report report = new Report();
        report.setTitle(request.getTitle());
        report.setDescription(request.getDescription());
        report.setHinhAnh(request.getHinhAnh());
        report.setCreatedReport(request.getCreatedReport());
        report.setReportedUser(request.getReportedUser());
        report.setReportedJob(request.getReportedJob());
        report.setStatusId(status);

        return ReportMapper.toDTO(reportRepository.save(report));
    }

    @Override
    public ReportDTO updateReport(int id, ReportRequest request) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Report với ID: " + id));

        report.setTitle(request.getTitle());
        report.setDescription(request.getDescription());
        report.setHinhAnh(request.getHinhAnh());
        report.setCreatedReport(request.getCreatedReport());
        report.setReportedUser(request.getReportedUser());
        report.setReportedJob(request.getReportedJob());

        ReportStatus status = reportStatusRepository.findById(request.getStatusId())
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy ReportStatus với ID: " + request.getStatusId()));
        report.setStatusId(status);

        return ReportMapper.toDTO(reportRepository.save(report));
    }

    @Override
    public void deleteReport(int id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Report với ID: " + id));
        reportRepository.delete(report);
    }
    @Override
    public List<ReportDTO> getAllReportsPage(ReportRequest request) {
        int pageNumber = request.getPageNumber();
        int pageSize = request.getPageSize();

        // Dùng phân trang cơ bản
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<Report> page = reportRepository.findAll(pageable);

        return page.getContent()
                .stream()
                .map(ReportMapper::toDTO)
                .collect(Collectors.toList());
    }
}
