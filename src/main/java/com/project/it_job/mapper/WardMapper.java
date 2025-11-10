package com.project.it_job.mapper;

import com.project.it_job.dto.WardDTO;
import com.project.it_job.entity.Province;
import com.project.it_job.entity.Ward;
import com.project.it_job.request.WardRequest;
import org.springframework.stereotype.Component;

@Component
public class WardMapper {

    public WardDTO toDTO(Ward entity) {
        if (entity == null) return null;

        WardDTO dto = new WardDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());

        if (entity.getProvince() != null) {
            dto.setIdProvince(entity.getProvince().getId());
        }

        return dto;
    }

    public Ward toEntity(WardRequest request) {
        if (request == null) return null;

        Ward entity = Ward.builder()
                .name(request.getName())
                .build();

        if (request.getIdProvince() != null && request.getIdProvince() > 0) {
            Province province = Province.builder()
                    .id(request.getIdProvince())
                    .build();
            entity.setProvince(province);
        }

        return entity;
    }


}
