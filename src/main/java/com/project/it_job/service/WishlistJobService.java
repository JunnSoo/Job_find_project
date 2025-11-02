package com.project.it_job.service;

import com.project.it_job.dto.WishlistJobDTO;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.WishlistJobRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WishlistJobService {
    List<WishlistJobDTO> getAllWishlistJobs();
    Page<WishlistJobDTO> getAllWishlistJobsPage(PageRequestCustom pageRequestCustom);
    List<WishlistJobDTO> getWishlistJobsByUserId(String userId);
    List<WishlistJobDTO> saveWishlistJobs(WishlistJobRequest wishlistJobRequest);
    List<WishlistJobDTO> deleteWishlistJobs(WishlistJobRequest wishlistJobRequest);
}
