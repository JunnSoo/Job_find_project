package com.project.it_job.mapper;

import com.project.it_job.dto.ProvinceDTO;
import com.project.it_job.dto.WardDTO;
import com.project.it_job.entity.Province;
import com.project.it_job.entity.Ward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProvinceMapper {

    @Autowired
    private WardMapper wardMapper;

    public ProvinceDTO toDTO(Province entity) {
        if (entity == null) return null;
        ProvinceDTO dto = new ProvinceDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());

        if (entity.getWards() != null) {
            List<WardDTO> wardDTOList = entity.getWards().stream()
                    .map(wardMapper::toDTO)
                    .collect(Collectors.toList());
            dto.setWards(wardDTOList);
        }

        return dto;
    }

    public Province toEntity(ProvinceDTO dto) {
        if (dto == null) return null;
        Province entity = new Province();
        entity.setId(dto.getId());
        entity.setName(dto.getName());

        if (dto.getWards() != null) {
            List<Ward> wardList = dto.getWards().stream()
                    .map(wardMapper::toEntity)
                    .collect(Collectors.toList());
            entity.setWards(wardList);
        }

        return entity;
    }
}
