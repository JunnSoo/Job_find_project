package com.project.it_job.service;

import com.project.it_job.dto.DegreeLevelDTO;
import com.project.it_job.request.DegreeLevelRequest;
import com.project.it_job.request.PageRequestCustom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DegreeLevelService {
    List<DegreeLevelDTO> getAll();
    Page<DegreeLevelDTO> getAllWithPage(PageRequestCustom req);
    DegreeLevelDTO getById(int id);
    DegreeLevelDTO create(DegreeLevelRequest request);
    DegreeLevelDTO update(int id, DegreeLevelRequest request);
    void delete(int id);
}
