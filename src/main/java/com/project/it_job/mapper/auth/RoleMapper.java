package com.project.it_job.mapper.auth;

import com.project.it_job.dto.auth.RoleDTO;
import com.project.it_job.entity.auth.Role;
import com.project.it_job.request.auth.RoleRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class RoleMapper {


    public RoleDTO toRoleDTO(Role role) {
        return RoleDTO.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
                .description(role.getDescription())
                .createdDate(role.getCreatedDate())
                .updatedDate(role.getUpdatedDate())
                .build();
    }

    public Role toRole(RoleDTO roleDTO) {
        return Role.builder()
                .id(roleDTO.getId())
                .roleName(roleDTO.getRoleName())
                .description(roleDTO.getDescription())
                .createdDate(roleDTO.getCreatedDate())
                .updatedDate(roleDTO.getUpdatedDate())
                .build();
    }

    public Role toCreateRole(RoleRequest roleRequest) {
        return Role.builder()
                .roleName(roleRequest.getRoleName())
                .description(roleRequest.getDescription())
                .createdDate(LocalDateTime.now())
                .build();
    }

    public void toUpdateRole(RoleRequest req, Role role) {
        if (req.getRoleName() != null) {
            role.setRoleName(req.getRoleName());
        }
        if (req.getDescription() != null) {
            role.setDescription(req.getDescription());
        }
        role.setUpdatedDate(LocalDateTime.now());
    }

    public List<RoleDTO> toRoleDTOList(List<Role> roles) {
        return roles.stream().map(this::toRoleDTO).toList();
    }

}
