package com.project.codinviec.mapper;

import com.project.codinviec.dto.DegreeLevelDTO;
import com.project.codinviec.entity.DegreeLevel;
import com.project.codinviec.request.DegreeLevelRequest;
import org.springframework.stereotype.Component;

@Component
public class DegreeLevelMapper {

    public DegreeLevelDTO toDTO(DegreeLevel entity) {
        if (entity == null) return null;
        return DegreeLevelDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public DegreeLevel saveDegreeLevel(DegreeLevelRequest request) {
        if (request == null) return null;
        return DegreeLevel.builder()
                .name(request.getName())
                .build();
    }

    public DegreeLevel updateDegreeLevel(Integer id, DegreeLevelRequest request) {
        if (request == null) return null;
        return DegreeLevel.builder()
                .id(id)
                .name(request.getName())
                .build();
    }
}
