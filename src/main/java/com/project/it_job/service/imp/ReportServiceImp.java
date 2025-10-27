package com.project.it_job.service.imp;

import com.project.it_job.dto.ReportDTO;
import com.project.it_job.entity.Report;
import com.project.it_job.entity.ReportStatus;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.ReportMapper;
import com.project.it_job.repository.ReportRepository;
import com.project.it_job.repository.ReportStatusRepository;
import com.project.it_job.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
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
        Optional<Report> reportOpt = reportRepository.findById(id);
        if (!reportOpt.isPresent()) {
            throw new NotFoundIdExceptionHandler("Không tìm thấy report với ID: " + id);
        }
        return ReportMapper.toDTO(reportOpt.get());
    }

    @Override
    public ReportDTO createReport(ReportDTO dto) {
        Optional<ReportStatus> statusOpt = reportStatusRepository.findById(dto.getStatusId());
        ReportStatus status = statusOpt.orElseThrow(() ->
                new NotFoundIdExceptionHandler("Không tìm thấy ReportStatus với ID: " + dto.getStatusId()));


        Report report = ReportMapper.toEntity(dto, status);
        return ReportMapper.toDTO(reportRepository.save(report));
    }

    @Override
    public ReportDTO updateReport(int id, ReportDTO dto) {
        Optional<Report> reportOpt = reportRepository.findById(id);
        if (!reportOpt.isPresent()) {
            throw new NotFoundIdExceptionHandler("Không tìm thấy report với ID: " + id);
        }

        Report report = reportOpt.get();
        report.setTitle(dto.getTitle());
        report.setDescription(dto.getDescription());
        report.setHinhAnh(dto.getHinhAnh());
        report.setReportedJob(dto.getReportedJob());
        report.setReportedUser(dto.getReportedUser());
        report.setCreatedReport(dto.getCreatedReport());

        Optional<ReportStatus> statusOpt = reportStatusRepository.findById(dto.getStatusId());
        statusOpt.ifPresent(report::setStatusId);

        return ReportMapper.toDTO(reportRepository.save(report));
    }

    @Override
    public void deleteReport(int id) {
        Optional<Report> reportOpt = reportRepository.findById(id);
        if (!reportOpt.isPresent()) {
            throw new NotFoundIdExceptionHandler("Không tìm thấy report với ID: " + id);
        }
        reportRepository.delete(reportOpt.get());
    }
}
