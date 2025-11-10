package com.project.it_job.mapper;

import com.project.it_job.dto.ProvinceDTO;
import com.project.it_job.dto.WardDTO;
import com.project.it_job.entity.Province;
import com.project.it_job.request.ProvinceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProvinceMapper {
    private final WardMapper wardMapper;

    public ProvinceDTO toDTO(Province entity) {
        if (entity == null) return null;

        ProvinceDTO dto = ProvinceDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();

        if (entity.getWards() != null) {
            List<WardDTO> wardDTOList = entity.getWards().stream()
                    .map(wardMapper::toDTO)
                    .collect(Collectors.toList());
            dto.setWards(wardDTOList);
        }

        return dto;
    }


    public Province toEntity(ProvinceRequest request) {
        if (request == null) return null;

        return Province.builder()
                .name(request.getName())
                .build();
    }

}
