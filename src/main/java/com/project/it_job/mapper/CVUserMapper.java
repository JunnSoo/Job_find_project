package com.project.it_job.mapper;

import com.project.it_job.dto.CVUserDTO;
import com.project.it_job.entity.CVUser;
import com.project.it_job.entity.auth.User;
import com.project.it_job.request.CVUserRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class CVUserMapper {
    public CVUserDTO toCVUserDTO(CVUser cvUser) {
        return CVUserDTO.builder()
                .id(cvUser.getId())
                .title(cvUser.getTitle())
                .candidateId(cvUser.getCandidate().getId())
                .version(cvUser.getVersion())
                .fileUrl(cvUser.getFileUrl())
                .createdAt(LocalDateTime.now())
                .isActive(cvUser.isActive())
                .build();
    }

    public CVUser toCVUser(CVUserDTO cvUserDTO, User candidate) {
        return CVUser.builder()
                .id(cvUserDTO.getId())
                .candidate(candidate)
                .title(cvUserDTO.getTitle())
                .version(cvUserDTO.getVersion())
                .fileUrl(cvUserDTO.getFileUrl())
                .createdAt(LocalDateTime.now())
                .isActive(cvUserDTO.isActive())
                .build();
    }

    public CVUser toCreateCVUser(CVUserRequest cvUserRequest,User candidate) {
        return CVUser.builder()
                .title(cvUserRequest.getTitle())
                .candidate(candidate)
                .fileUrl(cvUserRequest.getFileUrl())
                .createdAt(LocalDateTime.now())
                .isActive(cvUserRequest.getIsActive())
                .build();
    }

    public CVUser toUpdateCVUser(CVUser cvUser, CVUserRequest cvUserRequest) {
        if (cvUserRequest.getTitle() != null) {
            cvUser.setTitle(cvUserRequest.getTitle());
        }

        if(cvUserRequest.getFileUrl() != null) {
            cvUser.setFileUrl(cvUserRequest.getFileUrl());
        }


        if (cvUserRequest.getIsActive() != null) {
            cvUser.setActive(cvUserRequest.getIsActive());
        }

        cvUser.setVersion(cvUser.getId() + 1);
        return cvUser;
    }

    public List<CVUserDTO> cvUserDTOListToCVUserDTOList(List<CVUser> cvUserList) { return cvUserList.stream().map(this::toCVUserDTO).toList();}
}

