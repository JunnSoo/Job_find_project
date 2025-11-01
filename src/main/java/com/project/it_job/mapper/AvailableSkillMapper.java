package com.project.it_job.mapper;

import com.project.it_job.dto.AvailableSkillDto;
import com.project.it_job.entity.AvailableSkill;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
@Builder
public class AvailableSkillMapper {

    public AvailableSkillDto toDto(AvailableSkill entity) {
        if (entity == null) return null;
        return AvailableSkillDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
