package com.project.it_job.service.imp;

import com.project.it_job.dto.ReportStatusDTO;
import com.project.it_job.entity.ReportStatus;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.ReportStatusMapper;
import com.project.it_job.repository.ReportStatusRepository;
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

    @Override
    public List<ReportStatusDTO> getAll() {
        return reportStatusRepository.findAll()
                .stream()
                .map(ReportStatusMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReportStatusDTO getById(int id) {
        Optional<ReportStatus> opt = reportStatusRepository.findById(id);
        if (!opt.isPresent()) {
            throw new NotFoundIdExceptionHandler();
        }
        return ReportStatusMapper.toDTO(opt.get());
    }

    @Override
    public ReportStatusDTO create(ReportStatusDTO dto) {
        ReportStatus entity = ReportStatusMapper.toEntity(dto);
        return ReportStatusMapper.toDTO(reportStatusRepository.save(entity));
    }

    @Override
    public ReportStatusDTO update(int id, ReportStatusDTO dto) {
        Optional<ReportStatus> opt = reportStatusRepository.findById(id);
        if (!opt.isPresent()) {
            throw new NotFoundIdExceptionHandler();
        }

        ReportStatus entity = opt.get();
        entity.setName(dto.getName());
        return ReportStatusMapper.toDTO(reportStatusRepository.save(entity));
    }

    @Override
    public void delete(int id) {
        Optional<ReportStatus> opt = reportStatusRepository.findById(id);
        if (!opt.isPresent()) {
            throw new NotFoundIdExceptionHandler();
        }
        reportStatusRepository.delete(opt.get());
    }

}
