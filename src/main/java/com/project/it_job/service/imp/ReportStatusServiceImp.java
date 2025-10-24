package com.project.it_job.service.imp;

import com.project.it_job.entity.ReportStatus;
import com.project.it_job.repository.ReportStatusRepository;
import com.project.it_job.service.ReportStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportStatusServiceImp implements ReportStatusService {
    @Autowired
    private ReportStatusRepository reportStatusRepository;

    @Override
    public List<ReportStatus> getAll(){
        return reportStatusRepository.findAll();
    }

    @Override
    public Optional<ReportStatus> getById(int id) {
        return reportStatusRepository.findById(id);
    }

    @Override
    public ReportStatus create(ReportStatus reportStatus) {
        return reportStatusRepository.save(reportStatus);
    }

    @Override
    public ReportStatus update(int id, ReportStatus newData) {
        Optional<ReportStatus> optional = reportStatusRepository.findById(id);
        if (optional.isPresent()) {
            ReportStatus reportStatus = optional.get();
            reportStatus.setName(newData.getName());
            return reportStatusRepository.save(reportStatus);
        } else {
            System.out.println("ReportStatus ID " +id+ " not found");
            throw new RuntimeException("Not found" );
        }

    }

    @Override
    public boolean delete(int id) {
        Optional<ReportStatus> optional = reportStatusRepository.findById(id);
        if (optional.isPresent()) {
            reportStatusRepository.deleteById(id);
            return true;
        }
        System.out.println("ReportStatus ID " +id+ " not found to delete");
        return false;

    }

}
