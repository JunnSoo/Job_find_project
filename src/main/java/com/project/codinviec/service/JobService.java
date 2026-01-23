package com.project.codinviec.service;

import com.project.codinviec.dto.JobDTO;
import com.project.codinviec.request.ApplyJobRequest;
import com.project.codinviec.request.JobFilterRequest;
import com.project.codinviec.request.JobRequest;
import com.project.codinviec.request.PageRequestCustom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface JobService {
    List<JobDTO> getAllJob();
    Page<JobDTO> getAllJobPage(PageRequestCustom pageRequestCustom);
    Page<JobDTO> getAllJobPageWithFilter(JobFilterRequest jobFilterRequest);

    JobDTO getJobById(int id);
    List<JobDTO> getJobByIdCompany(String companyId);

    JobDTO createJob(JobRequest request);
    JobDTO updateJob(int id, JobRequest request);
    void deleteJob(int id);
    JobDTO applyJob(ApplyJobRequest applyJobRequest);
}
