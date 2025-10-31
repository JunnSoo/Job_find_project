package com.project.it_job.service;

import com.project.it_job.KeyEntity.WishlistCandidateKey;
import com.project.it_job.dto.WishlistCandidateDTO;
import com.project.it_job.request.WishlistCandidateRequest;

import java.util.List;

public interface WishlistCandidateService {
     List<WishlistCandidateDTO> getAllWishlistCandidates();
    List<WishlistCandidateDTO> getWishlistCandidateByHrId(String hrId);
    List<WishlistCandidateDTO> saveWishlistCandidate(WishlistCandidateRequest saveUpdateWishlistCandidate);
    List<WishlistCandidateDTO> deleteWistListCandidate(WishlistCandidateRequest saveUpdateWishlistCandidate);
}
