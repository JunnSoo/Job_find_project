package com.project.it_job.service;

import com.project.it_job.entity.EmploymentType;

import java.util.List;
import java.util.Optional;

public interface EmploymentTypeService {
    List<EmploymentType> getAll();
    Optional<EmploymentType> getById(int id);
    EmploymentType create(EmploymentType employmentType);
    EmploymentType update(int id,EmploymentType employmentType);
    boolean delete(int id);
}

