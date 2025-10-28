package com.project.it_job.mapper;

import com.project.it_job.dto.IndustryDTO;
import com.project.it_job.entity.Industry;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndustryMapper {
    public static IndustryDTO toDTO(Industry industry) {
        if(industry == null) return null;
        IndustryDTO industryDTO = new IndustryDTO();
        industryDTO.setId(industry.getId());
        industryDTO.setName(industry.getName());
        return industryDTO;
    }
    public static Industry toEntity(IndustryDTO industryDTO) {
        if(industryDTO == null) return null;
        Industry industry = new Industry();
        industry.setId(industryDTO.getId());
        industry.setName(industryDTO.getName());
        return industry;
    }
}
