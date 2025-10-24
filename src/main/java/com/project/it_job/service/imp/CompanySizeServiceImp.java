package com.project.it_job.service.imp;

import com.project.it_job.dto.CompanySizeDTO;
import com.project.it_job.entity.CompanySize;
import com.project.it_job.mapper.CompanySizeMapper;
import com.project.it_job.repository.CompanySizeRepository;
import com.project.it_job.service.CompanySizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanySizeServiceImp implements CompanySizeService {
    @Autowired
    private CompanySizeRepository companySizeRepository;

    @Autowired
    private CompanySizeMapper companySizeMapper;

    @Override
    public List<CompanySizeDTO> getAll() {
        return companySizeRepository.findAll()
                .stream()
                .map(companySizeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CompanySizeDTO> getById(int id) {
        return companySizeRepository.findById(id).map(companySizeMapper::toDTO);
    }

    @Override
    public CompanySizeDTO create(CompanySizeDTO dto) {
        CompanySize entity = companySizeMapper.toEntity(dto);
        return companySizeMapper.toDTO(companySizeRepository.save(entity));
    }

    @Override
    public CompanySizeDTO update(int id, CompanySizeDTO dto) {
        Optional<CompanySize> optional = companySizeRepository.findById(id);
        if (optional.isPresent()) {
            CompanySize existing = optional.get();
            existing.setMinEmployees(dto.getMinEmployees());
            existing.setMaxEmployees(dto.getMaxEmployees());
            return companySizeMapper.toDTO(companySizeRepository.save(existing));
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        if (companySizeRepository.existsById(id)) {
            companySizeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
