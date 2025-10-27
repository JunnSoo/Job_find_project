package com.project.it_job.service.auth;

import com.project.it_job.dto.auth.RoleDTO;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.auth.RoleRequest;
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
