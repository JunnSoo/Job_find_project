package com.project.it_job.mapper;

import com.project.it_job.dto.GroupCoreSkillDTO;
import com.project.it_job.entity.GroupCoreSkill;
import com.project.it_job.request.GroupCoreSkillRequest;
import org.springframework.stereotype.Component;

@Component
public class GroupCoreSkillMapper {
    public GroupCoreSkillDTO toDto(GroupCoreSkill entity) {
        if (entity == null) return null;
        return GroupCoreSkillDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public GroupCoreSkill saveGroupCoreSkill(GroupCoreSkillRequest request) {
        if (request == null) return null;
        return GroupCoreSkill.builder()
                .name(request.getName())
                .build();
    }

    public GroupCoreSkill updateGroupCoreSkill(Integer id, GroupCoreSkillRequest request) {
        if (request == null) return null;
        return GroupCoreSkill.builder()
                .id(id)
                .name(request.getName())
                .build();
    }
}
