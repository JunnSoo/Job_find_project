package com.project.it_job.service;

import com.project.it_job.dto.JobLevelDTO;
import com.project.it_job.request.JobLevelRequest;
import com.project.it_job.request.PageRequestCustom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface JobLevelService {
    List<JobLevelDTO> getAll();
    Page<JobLevelDTO> getAllWithPage(PageRequestCustom req);
    JobLevelDTO getById(int id);
    JobLevelDTO create(JobLevelRequest request);
    JobLevelDTO update(int id, JobLevelRequest request);
    void delete(int id);

}
