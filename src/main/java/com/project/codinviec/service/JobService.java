package com.project.codinviec.service;

import com.project.codinviec.dto.JobDTO;
import com.project.codinviec.request.JobRequest;
import com.project.codinviec.request.PageRequestCustom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface JobService {
    List<JobDTO> getAllJob();
    Page<JobDTO> getAllJobPage(PageRequestCustom pageRequestCustom);
    JobDTO getJobById(int id);
    JobDTO createJob(JobRequest request);
    JobDTO updateJob(int id, JobRequest request);
    void deleteJob(int id);


}
