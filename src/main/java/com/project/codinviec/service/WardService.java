package com.project.codinviec.service;

import com.project.codinviec.dto.WardDTO;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.WardRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WardService {
    List<WardDTO> getAll();
    Page<WardDTO> getAllWithPage(PageRequestCustom req);
    WardDTO getById(int id);
    WardDTO create(WardRequest request);
    WardDTO update(int id, WardRequest request);
    void delete(int id);
}
