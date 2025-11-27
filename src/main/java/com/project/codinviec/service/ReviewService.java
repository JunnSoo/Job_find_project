package com.project.codinviec.service;

import com.project.codinviec.dto.ReviewDTO;
import com.project.codinviec.dto.UserReviewDTO;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.SaveUpdateReviewRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> getAllReviews();
    Page<ReviewDTO> getAllReviewsPage(PageRequestCustom pageRequestCustom);
    ReviewDTO getReviewById(Integer id);
    ReviewDTO saveReview(SaveUpdateReviewRequest saveUpdateReviewRequest);
    ReviewDTO updateReview(Integer reviewId,SaveUpdateReviewRequest saveUpdateReviewRequest);
    ReviewDTO deleteReview(Integer reviewId);
    UserReviewDTO getReviewsByUserId(String userId);
    List<ReviewDTO> getReviewsByCompanyId(String companyId);

}
