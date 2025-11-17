package com.project.it_job.mapper;

import com.project.it_job.dto.AvailableSkillExperienceDTO;
import com.project.it_job.entity.AvailableSkill;
import com.project.it_job.entity.AvailableSkillExperience;
import com.project.it_job.entity.Experience;
import com.project.it_job.entity.GroupCoreSkill;
import com.project.it_job.entity.auth.User;
import com.project.it_job.request.AvailableSkillExperienceRequest;
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
