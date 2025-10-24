package com.project.it_job.mapper;

import com.project.it_job.dto.ProvinceDTO;
import com.project.it_job.entity.Province;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = WardMapper.class)
public interface ProvinceMapper {
    @Mapping(target = "wards", source = "wards")
    ProvinceDTO toDTO(Province province);

    @Mapping(target = "wards", source = "wards")
    Province toEntity(ProvinceDTO dto);
}
