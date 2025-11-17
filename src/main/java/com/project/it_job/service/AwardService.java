package com.project.it_job.service;

import com.project.it_job.dto.AwardDTO;
import com.project.it_job.request.AwardRequest;
import com.project.it_job.request.PageRequestCustom;
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
