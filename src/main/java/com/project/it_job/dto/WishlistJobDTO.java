package com.project.it_job.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WishlistJobDTO {
    private String idUser;
    private String firstName;
    private String lastName;
    private String avatar;
    private List<JobDTO> listJobDTOS;
}
