package com.project.it_job.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProvinceDTO {
    private int id;
    private String name;
    private List<WardDTO> wards;
}
