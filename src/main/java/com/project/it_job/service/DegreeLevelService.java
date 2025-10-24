package com.project.it_job.service;

import com.project.it_job.dto.DegreeLevelDTO;

import java.util.List;
import java.util.Optional;

public interface DegreeLevelService {
    List<DegreeLevelDTO> getAll();
    Optional<DegreeLevelDTO> getById(int id);
    DegreeLevelDTO create(DegreeLevelDTO dto);
    DegreeLevelDTO update(int id, DegreeLevelDTO dto);
    boolean delete(int id);
}
