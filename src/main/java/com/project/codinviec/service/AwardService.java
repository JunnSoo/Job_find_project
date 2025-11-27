package com.project.codinviec.service;

import com.project.codinviec.dto.AwardDTO;
import com.project.codinviec.request.AwardRequest;
import com.project.codinviec.request.PageRequestCustom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AwardService {
    List<AwardDTO> getAllAward();
    Page<AwardDTO> getAllAwardPage(PageRequestCustom pageRequestCustom);
    List<AwardDTO> getAwardByUser(String userId);
    AwardDTO getAwardById(Integer id);
    AwardDTO createAward(AwardRequest request);
    AwardDTO updateAward(int id, AwardRequest request);
    AwardDTO deleteAward(int id);
}
