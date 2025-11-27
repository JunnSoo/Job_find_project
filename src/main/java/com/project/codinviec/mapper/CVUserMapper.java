package com.project.codinviec.mapper;

import com.project.codinviec.dto.CVUserDTO;
import com.project.codinviec.entity.CVUser;
import com.project.codinviec.entity.auth.User;
import com.project.codinviec.request.CVUserRequest;
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

    public CVUser toCreateCVUser(CVUserRequest cvUserRequest, User candidate, String fileUrl) {
        return CVUser.builder()
                .title(cvUserRequest.getTitle())
                .candidate(candidate)
                .fileUrl(fileUrl)
                .createdAt(LocalDateTime.now())
                .isActive(cvUserRequest.getIsActive())
                .build();
    }

    public CVUser toUpdateCVUser(CVUser cvUser, CVUserRequest cvUserRequest, String fileUrl) {
        if (cvUserRequest.getTitle() != null) {
            cvUser.setTitle(cvUserRequest.getTitle());
        }

        cvUser.setFileUrl(fileUrl);

        if (cvUserRequest.getIsActive() != null) {
            cvUser.setActive(cvUserRequest.getIsActive());
        }

        cvUser.setVersion(cvUser.getId() + 1);
        return cvUser;
    }

    public List<CVUserDTO> cvUserDTOListToCVUserDTOList(List<CVUser> cvUserList) { return cvUserList.stream().map(this::toCVUserDTO).toList();}
}

