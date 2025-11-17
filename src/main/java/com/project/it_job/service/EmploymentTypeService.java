package com.project.it_job.service;

import com.project.it_job.dto.EmploymentTypeDTO;
import com.project.it_job.request.EmploymentTypeRequest;
import com.project.it_job.request.PageRequestCustom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmploymentTypeService {
    List<EmploymentTypeDTO> getAll();
    Page<EmploymentTypeDTO> getAllWithPage(PageRequestCustom req);
    EmploymentTypeDTO getById(int id);
    EmploymentTypeDTO create(EmploymentTypeRequest request);
    EmploymentTypeDTO update(int id,EmploymentTypeRequest request);
    void delete(int id);
}

