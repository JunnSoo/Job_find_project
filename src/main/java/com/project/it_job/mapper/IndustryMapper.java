package com.project.it_job.mapper;

import com.project.it_job.dto.IndustryDTO;
import com.project.it_job.entity.Industry;
import com.project.it_job.request.IndustryRequest;
import org.springframework.stereotype.Component;

@Component
public class IndustryMapper {
    public IndustryDTO toDTO(Industry industry) {
        if(industry == null) return null;
        return IndustryDTO.builder()
                .id(industry.getId())
                .name(industry.getName())
                .build();
    }

    public Industry toEntity(IndustryDTO industryDTO) {
        if(industryDTO == null) return null;
        return Industry.builder()
                .id(industryDTO.getId())
                .name(industryDTO.getName())
                .build();
    }

    public Industry saveIndustry(IndustryRequest request) {
        if(request == null) return null;
        return Industry.builder()
                .name(request.getName())
                .build();
    }

    public Industry updateIndustry(Integer id, IndustryRequest request) {
        if(request == null) return null;
        return Industry.builder()
                .id(id)
                .name(request.getName())
                .build();
    }
}
