package com.project.it_job.service;

import com.project.it_job.dto.EmploymentTypeDTO;
import com.project.it_job.entity.EmploymentType;
import com.project.it_job.request.EmploymentTypeRequest;

import java.util.List;
import java.util.Optional;

public interface EmploymentTypeService {
    List<EmploymentTypeDTO> getAll();
    EmploymentTypeDTO getById(int id);
    EmploymentTypeDTO create(EmploymentTypeRequest request);
    EmploymentTypeDTO update(int id,EmploymentTypeRequest request);
    void delete(int id);
}

