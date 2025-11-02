package com.project.it_job.mapper;

import com.project.it_job.dto.GroupCoreSkillDto;
import com.project.it_job.entity.GroupCoreSkill;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
@Builder
public class GroupCoreSkillMapper {
    public GroupCoreSkillDto toDto(GroupCoreSkill entity) {
        if (entity == null) return null;
        return GroupCoreSkillDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
