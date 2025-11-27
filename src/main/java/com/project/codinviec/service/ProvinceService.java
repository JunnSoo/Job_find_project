package com.project.codinviec.service;

import com.project.codinviec.dto.ProvinceDTO;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.ProvinceRequest;
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
