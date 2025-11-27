package com.project.codinviec.mapper;

import com.project.codinviec.dto.ExperienceDTO;
import com.project.codinviec.entity.Experience;
import com.project.codinviec.request.ExperienceRequest;
import org.springframework.stereotype.Component;

@Component
public class ExperienceMapper {
    public ExperienceDTO toDto(Experience experience) {
        if (experience == null) return null;
        return ExperienceDTO.builder()
                .id(experience.getId())
                .name(experience.getName())
                .build();
    }

    public Experience saveExperience(ExperienceRequest request) {
        if (request == null) return null;
        return Experience.builder()
                .name(request.getName())
                .build();
    }

    public Experience updateExperience(Integer id, ExperienceRequest request) {
        if (request == null) return null;
        return Experience.builder()
                .id(id)
                .name(request.getName())
                .build();
    }
}
