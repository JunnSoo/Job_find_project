package com.project.codinviec.mapper;

import com.project.codinviec.dto.StatusSpecialDTO;
import com.project.codinviec.entity.StatusSpecial;
import com.project.codinviec.entity.StatusSpecialCompany;
import com.project.codinviec.entity.StatusSpecialJob;
import com.project.codinviec.request.StatusSpecialRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StatusSpecialMapper {

    public StatusSpecialDTO toDTO(StatusSpecial entity) {
        if (entity == null) return null;
        return StatusSpecialDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .build();
    }

    public StatusSpecial saveStatusSpecial(StatusSpecialRequest request) {
        if (request == null) return null;
        return StatusSpecial.builder().title(request.getTitle()).build();
    }

    public StatusSpecial updateStatusSpecial(Integer id, StatusSpecialRequest request) {
        if (request == null) return null;
        return StatusSpecial.builder()
                .id(id)
                .title(request.getTitle()).build();
    }

    public List<StatusSpecialDTO> StatusSpecialJobToStatusSpecialDTO(List<StatusSpecialJob> statusSpecialJob) {
        List<StatusSpecialDTO> statusSpecialDTOS = new ArrayList<>();
        for(StatusSpecialJob item : statusSpecialJob){
            statusSpecialDTOS.add(toDTO(item.getIdStatusSpecial()));
        }
        return statusSpecialDTOS;
    }

    public List<StatusSpecialDTO> StatusSpecialCompanyToStatusSpecialDTO(List<StatusSpecialCompany> statusSpecialCompany) {
        List<StatusSpecialDTO> statusSpecialDTOS = new ArrayList<>();
        for(StatusSpecialCompany item : statusSpecialCompany){
            statusSpecialDTOS.add(toDTO(item.getIdStatusSpecial()));
        }
        return statusSpecialDTOS;
    }
}

