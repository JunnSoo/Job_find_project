package com.project.it_job.dto.auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryChilđTO {
    private int id;
    private String name;
}
