package com.project.codinviec.service;

import com.project.codinviec.dto.WishlistJobDTO;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.WishlistJobRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WishlistJobService {
    List<WishlistJobDTO> getAllWishlistJobs();
    Page<WishlistJobDTO> getAllWishlistJobsPage(PageRequestCustom pageRequestCustom);
    List<WishlistJobDTO> getWishlistJobsByUserId(String userId);
    List<WishlistJobDTO> saveWishlistJobs(WishlistJobRequest wishlistJobRequest);
    List<WishlistJobDTO> deleteWishlistJobs(WishlistJobRequest wishlistJobRequest);
}
