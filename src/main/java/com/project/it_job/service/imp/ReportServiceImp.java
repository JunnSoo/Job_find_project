package com.project.it_job.service.imp;

import com.project.it_job.entity.Report;
import com.project.it_job.repository.ReportRepository;
import com.project.it_job.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImp implements ReportService {

    @Autowired
    private ReportRepository reportRepository;




    @Override
    public List<Report> getAllReports(){
        return reportRepository.findAll();
    }

    @Override
    public Report getReportById(int id) {
        return reportRepository.findById(id).orElseThrow(() -> new RuntimeException("Report not found with id: " + id));
    }

    @Override
    public Report createReport(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public Report updateReport(int id, Report reportDetails) {
        Optional<Report> optionalReport = reportRepository.findById(id);
        if(optionalReport.isPresent()){
            Report report = optionalReport.get();
            report.setTitle(reportDetails.getTitle());
            report.setDescription(reportDetails.getDescription());
            report.setHinhAnh(reportDetails.getHinhAnh());
            report.setStatusId(reportDetails.getStatusId());
            report.setCreatedReport(reportDetails.getCreatedReport());
            report.setReportedUser(reportDetails.getReportedUser());
            report.setReportedJob(reportDetails.getReportedJob());
            return reportRepository.save(report);
        } else {
            throw new RuntimeException("Report not found with id: "+id);
        }

    }

    @Override
    public boolean deleteReport(int id) {
        if (reportRepository.existsById(id)) {
            reportRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
