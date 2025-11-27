package com.project.codinviec.service;

import com.project.codinviec.dto.EmploymentTypeDTO;
import com.project.codinviec.request.EmploymentTypeRequest;
import com.project.codinviec.request.PageRequestCustom;
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

