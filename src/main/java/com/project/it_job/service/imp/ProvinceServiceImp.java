package com.project.it_job.service.imp;

import com.project.it_job.dto.ProvinceDTO;
import com.project.it_job.entity.Province;
import com.project.it_job.mapper.ProvinceMapper;
import com.project.it_job.repository.ProvinceRepository;
import com.project.it_job.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProvinceServiceImp implements ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private ProvinceMapper provinceMapper;

    @Override
    public List<ProvinceDTO> getAll() {
        return provinceRepository.findAll()
                .stream()
                .map(provinceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProvinceDTO> getById(int id) {
        return provinceRepository.findById(id).map(provinceMapper::toDTO);
    }

    @Override
    public ProvinceDTO create(ProvinceDTO dto) {
        Province entity = provinceMapper.toEntity(dto);
        return provinceMapper.toDTO(provinceRepository.save(entity));
    }

    @Override
    public ProvinceDTO update(int id, ProvinceDTO dto) {
        Optional<Province> optional = provinceRepository.findById(id);
        if (optional.isPresent()) {
            Province existing = optional.get();
            existing.setName(dto.getName());
            return provinceMapper.toDTO(provinceRepository.save(existing));
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        if (provinceRepository.existsById(id)) {
            provinceRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
