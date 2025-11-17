package com.project.it_job.service;

import com.project.it_job.dto.IndustryDTO;
import com.project.it_job.request.IndustryRequest;
import com.project.it_job.request.PageRequestCustom;
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
