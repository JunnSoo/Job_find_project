package com.project.it_job.service;

import com.project.it_job.dto.AwardDto;
import com.project.it_job.request.AwardRequest;
import com.project.it_job.request.PageRequestCustom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AwardService {
    List<AwardDto> getAllAward();
    Page<AwardDto> getAllAwardPage(PageRequestCustom pageRequestCustom);
    List<AwardDto> getAwardByUser(String userId);
    AwardDto getAwardById(Integer id);
    AwardDto createAward(AwardRequest request);
    AwardDto updateAward(int id, AwardRequest request);
    AwardDto deleteAward(int id);
}
