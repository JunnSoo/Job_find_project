package com.project.it_job.service;

import com.project.it_job.dto.IndustryDTO;
import com.project.it_job.request.IndustryRequest;

import java.util.List;

public interface IndustryService {
    List<IndustryDTO> getAll();
    IndustryDTO getById(int id);
    IndustryDTO create(IndustryRequest industry);
    IndustryDTO update(int id, IndustryRequest industry);
    void delete(int id);
}
