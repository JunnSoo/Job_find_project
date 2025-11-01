package com.project.it_job.mapper;

import com.project.it_job.dto.ExperienceDto;
import com.project.it_job.entity.Experience;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
@Builder
public class ExperienceMapper {
    public ExperienceDto toDto(Experience experience) {
        if (experience == null) return null;
        return ExperienceDto.builder()
                .id(experience.getId())
                .name(experience.getName())
                .build();
    }
}
