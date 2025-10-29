package com.project.it_job.service;

import com.project.it_job.dto.JobDTO;
import com.project.it_job.request.JobRequest;
import com.project.it_job.request.PageRequestCustom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface JobService {
    List<JobDTO> getAllJob();
    Page<JobDTO> getALLjobPage(PageRequestCustom pageRequestCustom);
    JobDTO getJobById(int id);
    JobDTO createJob(JobRequest request);
    JobDTO updateJob(int id, JobRequest request);
    void deleteJob(int id);


}
