package com.project.it_job.mapper;

import com.project.it_job.dto.WardDTO;
import com.project.it_job.entity.Ward;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WardMapper {
    @Mapping(source = "province.id", target = "idProvince")
    WardDTO toDTO(Ward ward);

    @Mapping(source = "idProvince", target = "province.id")
    Ward toEntity(WardDTO dto);

}
