package com.project.it_job.service.imp;

import com.project.it_job.dto.DegreeLevelDTO;
import com.project.it_job.entity.DegreeLevel;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.DegreeLevelMapper;
import com.project.it_job.repository.DegreeLevelRepository;
import com.project.it_job.request.DegreeLevelRequest;
import com.project.it_job.service.DegreeLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DegreeLevelServiceImp implements DegreeLevelService {

    private final DegreeLevelRepository degreeLevelRepository;

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
    public DegreeLevelDTO getById(int id) {
        DegreeLevel degreeLevel = degreeLevelRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Không tìm thấy DegreeLevel ID: " + id));
        return degreeLevelMapper.toDTO(degreeLevel);
    }

    @Override
    public DegreeLevelDTO create(DegreeLevelRequest request) {
        DegreeLevel entity = new DegreeLevel();
        entity.setName(request.getName());
        return degreeLevelMapper.toDTO(degreeLevelRepository.save(entity));
    }

    @Override
    public DegreeLevelDTO update(int id, DegreeLevelRequest dto) {
        DegreeLevel degreeLevel = degreeLevelRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy DegreeLevel ID: " + id));
        degreeLevel.setName(dto.getName());

        return degreeLevelMapper.toDTO(degreeLevelRepository.save(degreeLevel));

    }

    @Override
    public void delete(int id) {
        DegreeLevel degreeLevel = degreeLevelRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy DegreeLevel ID: " + id));
        degreeLevelRepository.delete(degreeLevel);
    }
}
