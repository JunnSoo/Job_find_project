package com.project.it_job.service.imp;

import com.project.it_job.dto.EmploymentTypeDTO;
import com.project.it_job.entity.EmploymentType;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.EmploymentTypeMapper;
import com.project.it_job.repository.EmploymentTypeRepository;
import com.project.it_job.request.EmploymentTypeRequest;
import com.project.it_job.service.EmploymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmploymentTypeServiceImp implements EmploymentTypeService {
    @Autowired
    EmploymentTypeRepository employmentTypeRepository;

    @Override
    public List<EmploymentTypeDTO> getAll() {
        return employmentTypeRepository.findAll()
                .stream()
                .map(EmploymentTypeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmploymentTypeDTO getById(int id) {
        EmploymentType employmentType = employmentTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy EmploymentType với ID: " + id));
        return EmploymentTypeMapper.toDTO(employmentType);
    }

    @Override
    public EmploymentTypeDTO create(EmploymentTypeRequest employmentType) {
        EmploymentType employmentTypeEntity = new EmploymentType();
        employmentTypeEntity.setName(employmentType.getName());
        return EmploymentTypeMapper.toDTO(employmentTypeRepository.save(employmentTypeEntity));
    }

    @Override
    public EmploymentTypeDTO update(int id, EmploymentTypeRequest request) {
        EmploymentType employmentType = employmentTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy EmploymentType ID: " + id));
        employmentType.setName(request.getName());
        return EmploymentTypeMapper.toDTO(employmentTypeRepository.save(employmentType));
    }

    @Override
    public void delete(int id) {
       EmploymentType employmentType = employmentTypeRepository.findById(id)
               .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy EmploymentType ID: " + id));
       employmentTypeRepository.delete(employmentType);
    }



}
