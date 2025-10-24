package com.project.it_job.service.imp;

import com.project.it_job.dto.DegreeLevelDTO;
import com.project.it_job.entity.DegreeLevel;
import com.project.it_job.mapper.DegreeLevelMapper;
import com.project.it_job.repository.DegreeLevelRepository;
import com.project.it_job.service.DegreeLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DegreeLevelServiceImp implements DegreeLevelService {
    @Autowired
    private DegreeLevelRepository degreeLevelRepository;

    @Autowired
    private DegreeLevelMapper degreeLevelMapper;

    @Override
    public List<DegreeLevelDTO> getAll() {
        return degreeLevelRepository.findAll()
                .stream()
                .map(degreeLevelMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DegreeLevelDTO> getById(int id) {
        return degreeLevelRepository.findById(id).map(degreeLevelMapper::toDTO);
    }

    @Override
    public DegreeLevelDTO create(DegreeLevelDTO dto) {
        DegreeLevel entity = degreeLevelMapper.toEntity(dto);
        return degreeLevelMapper.toDTO(degreeLevelRepository.save(entity));
    }

    @Override
    public DegreeLevelDTO update(int id, DegreeLevelDTO dto) {
        Optional<DegreeLevel> optional = degreeLevelRepository.findById(id);
        if (optional.isPresent()) {
            DegreeLevel existing = optional.get();
            existing.setName(dto.getName());
            return degreeLevelMapper.toDTO(degreeLevelRepository.save(existing));
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        if (degreeLevelRepository.existsById(id)) {
            degreeLevelRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
