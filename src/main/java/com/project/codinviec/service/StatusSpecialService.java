package com.project.codinviec.service;

import com.project.codinviec.dto.JobDTO;
import com.project.codinviec.dto.StatusSpecialDTO;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.StatusSpecialRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StatusSpecialService {
    List<StatusSpecialDTO> getAll();
    Page<StatusSpecialDTO> getAllWithPage(PageRequestCustom req);
    StatusSpecialDTO getById(int id);
    StatusSpecialDTO create(StatusSpecialRequest request);
    StatusSpecialDTO update(int id, StatusSpecialRequest request);
    StatusSpecialDTO delete(int id);
}

