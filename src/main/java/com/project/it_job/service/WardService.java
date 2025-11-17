package com.project.it_job.service;

import com.project.it_job.dto.WardDTO;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.WardRequest;
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
