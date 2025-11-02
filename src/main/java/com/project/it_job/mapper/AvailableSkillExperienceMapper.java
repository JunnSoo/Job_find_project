package com.project.it_job.mapper;

import com.project.it_job.dto.AvailableSkillExperienceDto;
import com.project.it_job.entity.AvailableSkillExperience;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
@Builder
public class AvailableSkillExperienceMapper {

    public AvailableSkillExperienceDto toDto(AvailableSkillExperience ase) {
        if (ase == null) return null;
        return AvailableSkillExperienceDto.builder()
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
}
