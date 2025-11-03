package com.project.it_job.service.imp;

import com.project.it_job.dto.ReportStatusDTO;
import com.project.it_job.entity.ReportStatus;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.ReportStatusMapper;
import com.project.it_job.repository.ReportStatusRepository;
import com.project.it_job.request.ReportStatusRequest;
import com.project.it_job.service.ReportStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportStatusServiceImp implements ReportStatusService {

    @Autowired
    private ReportStatusRepository reportStatusRepository;
    @Autowired
    private ReportStatusMapper reportStatusMapper;

    @Override
    public List<ReportStatusDTO> getAllStatus() {
        return reportStatusRepository.findAll()
                .stream()
                .map(reportStatusMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReportStatusDTO getStatusById(int id) {
        ReportStatus status = reportStatusRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy ReportStatus với ID: " + id));
        return reportStatusMapper.toDTO(status);
    }

    @Override
    public ReportStatusDTO createStatus(ReportStatusRequest request) {
        ReportStatus status = new ReportStatus();
        status.setName(request.getName());
        return reportStatusMapper.toDTO(reportStatusRepository.save(status));
    }

    @Override
    public ReportStatusDTO updateStatus(int id, ReportStatusRequest request) {
        ReportStatus status = reportStatusRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy ReportStatus với ID: " + id));
        status.setName(request.getName());
        return reportStatusMapper.toDTO(reportStatusRepository.save(status));
    }

    @Override
    public void delete(int id) {
        ReportStatus status = reportStatusRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy ReportStatus với ID: " + id));
        reportStatusRepository.delete(status);
    }
}
