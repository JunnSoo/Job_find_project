package com.project.it_job.service.imp;

import com.project.it_job.dto.ProvinceDTO;
import com.project.it_job.entity.Province;
import com.project.it_job.entity.Ward;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.ProvinceMapper;
import com.project.it_job.repository.ProvinceRepository;
import com.project.it_job.repository.WardRepository;
import com.project.it_job.request.ProvinceRequest;
import com.project.it_job.service.ProvinceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProvinceServiceImp implements ProvinceService {
    private final ProvinceRepository provinceRepository;
    private final ProvinceMapper provinceMapper;
    private final WardRepository wardRepository;

    @Override
    public List<ProvinceDTO> getAll() {
        return provinceRepository.findAll()
                .stream()
                .map(provinceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProvinceDTO getById(int id) {
        return provinceRepository.findById(id).map(provinceMapper::toDTO).orElse(null);
    }

    @Override
    @Transactional
    public ProvinceDTO create(ProvinceRequest request) {
        Province province = provinceMapper.toEntity(request);

        if (request.getWardId() != null && request.getWardId() > 0) {
            Ward ward = wardRepository.findById(request.getWardId())
                    .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Ward id: " + request.getWardId()));

            ward.setProvince(province);
            province.setWards(List.of(ward));
        }

        Province saved = provinceRepository.save(province);
        return provinceMapper.toDTO(saved);
    }

    @Override
    @Transactional
    public ProvinceDTO update(Integer id, ProvinceRequest request) {
        Province province = provinceRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Province id: " + id));

        province.setName(request.getName());

        if (request.getWardId() != null && request.getWardId() > 0) {
            Ward ward = wardRepository.findById(request.getWardId())
                    .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Ward id: " + request.getWardId()));

            ward.setProvince(province);

            if (province.getWards() == null) {
                province.setWards(new ArrayList<>());
            }

            if (!province.getWards().contains(ward)) {
                province.getWards().add(ward);
            }
        }

        Province savedProvince = provinceRepository.save(province);

        return provinceMapper.toDTO(savedProvince);
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
