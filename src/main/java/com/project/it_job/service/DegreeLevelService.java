package com.project.it_job.service;

import com.project.it_job.dto.DegreeLevelDTO;
import com.project.it_job.request.DegreeLevelRequest;

import java.util.List;
import java.util.Optional;

public interface DegreeLevelService {
    List<DegreeLevelDTO> getAll();
    DegreeLevelDTO getById(int id);
    DegreeLevelDTO create(DegreeLevelRequest request);
    DegreeLevelDTO update(int id, DegreeLevelRequest request);
    void delete(int id);
}
