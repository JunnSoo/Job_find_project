package com.project.codinviec.service;

import com.project.codinviec.dto.JobLevelDTO;
import com.project.codinviec.request.JobLevelRequest;
import com.project.codinviec.request.PageRequestCustom;
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
