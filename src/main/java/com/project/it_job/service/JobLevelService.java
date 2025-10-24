package com.project.it_job.service;

import com.project.it_job.dto.JobLevelDTO;

import java.util.List;
import java.util.Optional;

public interface JobLevelService {
    List<JobLevelDTO> getAll();
    Optional<JobLevelDTO> getById(int id);
    JobLevelDTO create(JobLevelDTO dto);
    JobLevelDTO update(int id, JobLevelDTO dto);
    boolean delete(int id);
}
