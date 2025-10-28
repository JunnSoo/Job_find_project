package com.project.it_job.service;

import com.project.it_job.dto.JobLevelDTO;
import com.project.it_job.request.JobLevelRequest;

import java.util.List;

public interface JobLevelService {
    List<JobLevelDTO> getAll();
    JobLevelDTO getById(int id);
    JobLevelDTO create(JobLevelRequest request);
    JobLevelDTO update(int id, JobLevelRequest request);
    void delete(int id);

}
