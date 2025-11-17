package com.project.it_job.service;

import com.project.it_job.dto.ProvinceDTO;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.ProvinceRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProvinceService {
    List<ProvinceDTO> getAll();
    Page<ProvinceDTO> getAllWithPage(PageRequestCustom req);
    ProvinceDTO getById(int id);
    ProvinceDTO create(ProvinceRequest request);
    ProvinceDTO update(int id, ProvinceRequest request);
    void delete(int id);
}
