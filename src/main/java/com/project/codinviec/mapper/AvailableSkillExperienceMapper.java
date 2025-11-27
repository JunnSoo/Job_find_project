package com.project.codinviec.mapper;

import com.project.codinviec.dto.AvailableSkillExperienceDTO;
import com.project.codinviec.entity.AvailableSkill;
import com.project.codinviec.entity.AvailableSkillExperience;
import com.project.codinviec.entity.Experience;
import com.project.codinviec.entity.GroupCoreSkill;
import com.project.codinviec.entity.auth.User;
import com.project.codinviec.request.AvailableSkillExperienceRequest;
import org.springframework.stereotype.Component;

@Component
public class AvailableSkillExperienceMapper {

    public AvailableSkillExperienceDTO toDto(AvailableSkillExperience ase) {
        if (ase == null) return null;
        return AvailableSkillExperienceDTO.builder()
                .id(ase.getId())
                .userId(ase.getUser() != null ? ase.getUser().getId() : null)

                .groupCoreId(ase.getGroupCoreSkill() != null ? ase.getGroupCoreSkill().getId() : null)
                .groupCoreName(ase.getGroupCoreSkill() != null ? ase.getGroupCoreSkill().getName() : null)

                .availableSkillId(ase.getAvailableSkill() != null ? ase.getAvailableSkill().getId() : null)
                .availableSkillName(ase.getAvailableSkill() != null ? ase.getAvailableSkill().getName() : null)

                .experienceId(ase.getExperience() != null ? ase.getExperience().getId() : null)
                .experienceName(ase.getExperience() != null ? ase.getExperience().getName() : null)
                .build();
    }

    public AvailableSkillExperience saveAvailableSkillExperience(User user, GroupCoreSkill groupCoreSkill, AvailableSkill availableSkill, Experience experience, AvailableSkillExperienceRequest request) {
        if (request == null) return null;
        return AvailableSkillExperience.builder()
                .user(user)
                .groupCoreSkill(groupCoreSkill)
                .availableSkill(availableSkill)
                .experience(experience)
                .build();
    }

    public AvailableSkillExperience updateAvailableSkillExperience(Integer id, User user, GroupCoreSkill groupCoreSkill, AvailableSkill availableSkill, Experience experience, AvailableSkillExperienceRequest request) {
        if (request == null) return null;
        return AvailableSkillExperience.builder()
                .id(id)
                .user(user)
                .groupCoreSkill(groupCoreSkill)
                .availableSkill(availableSkill)
                .experience(experience)
                .build();
    }
}
