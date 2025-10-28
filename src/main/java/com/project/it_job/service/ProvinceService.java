package com.project.it_job.service;

import com.project.it_job.dto.ProvinceDTO;

import java.util.List;
import java.util.Optional;

public interface ProvinceService {
    List<ProvinceDTO> getAll();
    ProvinceDTO getById(int id);
    ProvinceDTO create(ProvinceDTO dto);
    ProvinceDTO update(int id, ProvinceDTO dto);
    boolean delete(int id);
}
