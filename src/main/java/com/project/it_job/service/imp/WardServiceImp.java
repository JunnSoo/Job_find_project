package com.project.it_job.service.imp;

import com.project.it_job.dto.WardDTO;
import com.project.it_job.entity.Ward;
import com.project.it_job.mapper.WardMapper;
import com.project.it_job.repository.WardRepository;
import com.project.it_job.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WardServiceImp implements WardService {
    @Autowired
    private WardRepository wardRepository;

    @Autowired
    private WardMapper wardMapper;

    @Override
    public List<WardDTO> getAll() {
        return wardRepository.findAll()
                .stream()
                .map(wardMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public WardDTO getById(int id) {
        return wardRepository.findById(id).map(wardMapper::toDTO).orElse(null);
    }

    @Override
    public WardDTO create(WardDTO dto) {
        Ward entity = wardMapper.toEntity(dto);
        return wardMapper.toDTO(wardRepository.save(entity));
    }

    @Override
    public WardDTO update(int id, WardDTO dto) {
        Optional<Ward> optional = wardRepository.findById(id);
        if (optional.isPresent()) {
            Ward existing = optional.get();
            existing.setName(dto.getName());
            return wardMapper.toDTO(wardRepository.save(existing));
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        if (wardRepository.existsById(id)) {
            wardRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
