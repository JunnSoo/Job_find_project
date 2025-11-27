package com.project.codinviec.service;

import com.project.codinviec.dto.DegreeLevelDTO;
import com.project.codinviec.request.DegreeLevelRequest;
import com.project.codinviec.request.PageRequestCustom;
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
