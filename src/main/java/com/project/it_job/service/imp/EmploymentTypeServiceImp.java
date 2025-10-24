package com.project.it_job.service.imp;

import com.project.it_job.entity.EmploymentType;
import com.project.it_job.repository.EmploymentTypeRepository;
import com.project.it_job.service.EmploymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmploymentTypeServiceImp implements EmploymentTypeService {
    @Autowired
    EmploymentTypeRepository employmentTypeRepository;

    @Override
    public List<EmploymentType> getAll() {
        return employmentTypeRepository.findAll();
    }

    @Override
    public Optional<EmploymentType> getById(int id) {
        return employmentTypeRepository.findById(id);
    }

    @Override
    public EmploymentType create(EmploymentType employmentType) {
        return employmentTypeRepository.save(employmentType);
    }

    @Override
    public EmploymentType update(int id, EmploymentType employmentType) {
        Optional<EmploymentType> existing = employmentTypeRepository.findById(id);
        if (existing.isPresent()) {
            EmploymentType updateEntity = existing.get();
            updateEntity.setName(employmentType.getName());
            return employmentTypeRepository.save(updateEntity);
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        Optional<EmploymentType> existing = employmentTypeRepository.findById(id);
        if (existing.isPresent()) {
            employmentTypeRepository.deleteById(id);
            return true;
        }
        return false;
    }



}
