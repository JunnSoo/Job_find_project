package com.project.it_job.service;

import com.project.it_job.dto.CompanySizeDTO;

import java.util.List;
import java.util.Optional;

public interface CompanySizeService {
    List<CompanySizeDTO> getAll();
    Optional<CompanySizeDTO> getById(int id);
    CompanySizeDTO create(CompanySizeDTO dto);
    CompanySizeDTO update(int id, CompanySizeDTO dto);
    boolean delete(int id);
}
