package com.project.codinviec.dto;

import com.project.codinviec.dto.auth.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobUserApplyDTO {
    private List<UserDTO> listUsers;
    private List<JobDTO> listJobs;
}
