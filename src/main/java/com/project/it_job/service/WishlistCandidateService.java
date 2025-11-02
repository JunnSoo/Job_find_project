package com.project.it_job.service;

import com.project.it_job.dto.WishlistCandidateDTO;
import com.project.it_job.entity.WishlistCandidate;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.WishlistCandidateRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WishlistCandidateService {
     List<WishlistCandidateDTO> getAllWishlistCandidates();
     Page<WishlistCandidateDTO> getAllWishlistCandidatesPage(PageRequestCustom pageRequestCustom);
    List<WishlistCandidateDTO> getWishlistCandidateByHrId(String hrId);
    List<WishlistCandidateDTO> saveWishlistCandidate(WishlistCandidateRequest saveUpdateWishlistCandidate);
    List<WishlistCandidateDTO> deleteWistListCandidate(WishlistCandidateRequest saveUpdateWishlistCandidate);
}
