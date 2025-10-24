package com.project.it_job.mapper;

import com.project.it_job.dto.WardDTO;
import com.project.it_job.entity.Province;
import com.project.it_job.entity.Ward;
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

    public Ward toEntity(WardDTO dto) {
        if (dto == null) return null;
        Ward entity = new Ward();
        entity.setId(dto.getId());
        entity.setName(dto.getName());

        if (dto.getIdProvince() > 0) {
            Province province = new Province();
            province.setId(dto.getIdProvince());
            entity.setProvince(province);
        }

        return entity;
    }
}
