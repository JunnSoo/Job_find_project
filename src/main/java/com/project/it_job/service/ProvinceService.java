package com.project.it_job.service;

import com.project.it_job.dto.ProvinceDTO;
import com.project.it_job.request.ProvinceRequest;

import java.util.List;


public interface ProvinceService {
    List<ProvinceDTO> getAll();
    ProvinceDTO getById(int id);
    ProvinceDTO create(ProvinceRequest request);
    ProvinceDTO update(Integer id, ProvinceRequest request);
    boolean delete(int id);
}
