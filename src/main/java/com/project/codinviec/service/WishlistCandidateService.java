package com.project.codinviec.service;

import com.project.codinviec.dto.WishlistCandidateDTO;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.WishlistCandidateRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WishlistCandidateService {
     List<WishlistCandidateDTO> getAllWishlistCandidates();
     Page<WishlistCandidateDTO> getAllWishlistCandidatesPage(PageRequestCustom pageRequestCustom);
    List<WishlistCandidateDTO> getWishlistCandidateByHrId(String hrId);
    List<WishlistCandidateDTO> saveWishlistCandidate(WishlistCandidateRequest saveUpdateWishlistCandidate);
    List<WishlistCandidateDTO> deleteWistListCandidate(WishlistCandidateRequest saveUpdateWishlistCandidate);
}
