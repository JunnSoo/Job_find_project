package com.project.it_job.mapper;

import com.project.it_job.dto.AwardDto;
import com.project.it_job.entity.Award;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
@Builder
public class AwardMapper {

    public AwardDto toDto(Award award) {
        if (award == null) return null;
        return AwardDto.builder()
                .id(award.getId())
                .userId(award.getUser() != null ? award.getUser().getId() : null)
                .awardName(award.getAwardName())
                .organization(award.getOrganization())
                .date(award.getDate())
                .description(award.getDescription())
                .build();
    }
}
