package com.project.codinviec.service;

import com.project.codinviec.dto.IndustryDTO;
import com.project.codinviec.request.IndustryRequest;
import com.project.codinviec.request.PageRequestCustom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IndustryService {
    List<IndustryDTO> getAll();
    Page<IndustryDTO> getAllWithPage(PageRequestCustom req);
    IndustryDTO getById(int id);
    IndustryDTO create(IndustryRequest industry);
    IndustryDTO update(int id, IndustryRequest industry);
    void delete(int id);
}
