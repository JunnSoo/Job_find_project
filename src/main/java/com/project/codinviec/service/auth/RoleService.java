package com.project.codinviec.service.auth;

import com.project.codinviec.dto.auth.RoleDTO;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.auth.RoleRequest;
import org.springframework.data.domain.Page;

import java.util.List;


public interface RoleService {
    List<RoleDTO> getAll();
    Page<RoleDTO> getAllWithPage(PageRequestCustom req);
    RoleDTO getById(String id);
    RoleDTO create(RoleRequest req);
    RoleDTO update(String id, RoleRequest req);
    RoleDTO deleteById(String id);
}
