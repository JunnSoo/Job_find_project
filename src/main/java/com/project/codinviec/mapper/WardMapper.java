package com.project.codinviec.mapper;

import com.project.codinviec.dto.WardDTO;
import com.project.codinviec.entity.Province;
import com.project.codinviec.entity.Ward;
import com.project.codinviec.request.WardRequest;
import org.springframework.stereotype.Component;

@Component
public class WardMapper {

    public WardDTO toDTO(Ward entity) {
        if (entity == null)
            return null;
        return WardDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .idProvince(entity.getProvince() != null ? entity.getProvince().getId() : 0)
                .build();
    }

    public Ward toEntity(WardDTO dto) {
        if (dto == null)
            return null;
        return Ward.builder()
                .id(dto.getId())
                .name(dto.getName())
                .province(dto.getIdProvince() > 0 ? Province.builder()
                        .id(dto.getIdProvince())
                        .build() : null)
                .build();
    }

    public Ward saveWard(WardRequest request) {
        if (request == null)
            return null;
        return Ward.builder()
                .name(request.getName())
                .province(Province.builder()
                        .id(request.getIdProvince())
                        .build())
                .build();
    }

    public Ward updateWard(Integer id, WardRequest request) {
        if (request == null)
            return null;
        return Ward.builder()
                .id(id)
                .name(request.getName())
                .province(Province.builder()
                        .id(request.getIdProvince())
                        .build())
                .build();
    }
}
