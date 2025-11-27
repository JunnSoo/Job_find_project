package com.project.codinviec.service;

import com.project.codinviec.dto.CVUserDTO;
import com.project.codinviec.request.CVUserRequest;
import com.project.codinviec.request.PageRequestCustom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CVUserService {
    List<CVUserDTO> getAll();
    Page<CVUserDTO> getAllWithPage(PageRequestCustom req);
    CVUserDTO getById(Integer id);
    CVUserDTO create(CVUserRequest req);
    CVUserDTO update(Integer id, CVUserRequest req);
    CVUserDTO deleteById(Integer id, String candidateId);
}
