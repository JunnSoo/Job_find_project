package com.project.it_job.service;

import com.project.it_job.dto.CVUserDTO;
import com.project.it_job.request.CVUserRequest;
import com.project.it_job.request.PageRequestCustom;
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
