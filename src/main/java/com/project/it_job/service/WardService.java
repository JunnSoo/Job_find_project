package com.project.it_job.service;

import com.project.it_job.dto.WardDTO;

import java.util.List;
import java.util.Optional;

public interface WardService {
    List<WardDTO> getAll();
    Optional<WardDTO> getById(int id);
    WardDTO create(WardDTO dto);
    WardDTO update(int id, WardDTO dto);
    boolean delete(int id);
}
