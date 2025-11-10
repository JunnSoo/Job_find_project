package com.project.it_job.service.imp;

import com.project.it_job.dto.WardDTO;
import com.project.it_job.entity.Province;
import com.project.it_job.entity.Ward;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.WardMapper;
import com.project.it_job.repository.ProvinceRepository;
import com.project.it_job.repository.WardRepository;
import com.project.it_job.request.WardRequest;
import com.project.it_job.service.WardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WardServiceImp implements WardService {
    private final WardRepository wardRepository;
    private final ProvinceRepository provinceRepository;
    private final WardMapper wardMapper;

    @Override
    public List<WardDTO> getAll() {
        return wardRepository.findAll()
                .stream()
                .map(wardMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public WardDTO getById(int id) {
        return wardRepository.findById(id).map(wardMapper::toDTO)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id Ward"));
    }

    @Override
    public WardDTO create(WardRequest request) {
        Ward entity = wardMapper.toEntity(request);
        return wardMapper.toDTO(wardRepository.save(entity));
    }

    @Override
    public WardDTO update(Integer id, WardRequest request) {
        Ward ward = wardRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id Ward"));

        Province province= provinceRepository.findById(request.getIdProvince())
                        .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id Province"));

        ward.setName(request.getName());
        ward.setProvince(province);

        return wardMapper.toDTO(wardRepository.save(ward));
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
