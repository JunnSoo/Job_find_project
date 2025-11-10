package com.project.it_job.service;

import com.project.it_job.dto.WardDTO;
import com.project.it_job.request.WardRequest;

import java.util.List;


public interface WardService {
    List<WardDTO> getAll();
    WardDTO getById(int id);
    WardDTO create(WardRequest request);
    WardDTO update(Integer id, WardRequest request);
    boolean delete(int id);
}
