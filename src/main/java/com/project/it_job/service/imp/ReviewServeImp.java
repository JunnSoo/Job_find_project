package com.project.it_job.service.imp;

import com.project.it_job.dto.ReviewDTO;
import com.project.it_job.entity.Review;
import com.project.it_job.entity.auth.Company;
import com.project.it_job.entity.auth.User;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.ReviewMapper;
import com.project.it_job.repository.ReviewRepository;
import com.project.it_job.repository.auth.CompanyRepository;
import com.project.it_job.repository.auth.UserRepository;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.SaveUpdateReviewRequest;
import com.project.it_job.service.ReviewService;
import com.project.it_job.specification.ReviewSpecification;
import com.project.it_job.util.PageCustomHelpper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServeImp implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final PageCustomHelpper pageCustomHelpper;
    private final ReviewSpecification reviewSpecification;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    @Override
    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll().stream().map(review -> reviewMapper.reviewToReviewDTO(review)).toList();
    }

    @Override
    public Page<ReviewDTO> getAllReviewsPage(PageRequestCustom pageRequestCustom) {
        //        validate pageCustom
        PageRequestCustom pageRequestValidate = pageCustomHelpper.validatePageCustom(pageRequestCustom);

//        Tạo page cho api
        Pageable pageable = PageRequest.of(pageRequestValidate.getPageNumber() - 1,pageRequestValidate.getPageSize());

//        Tạo search

        Specification<Review> spec = Specification.allOf(reviewSpecification.searchByName(pageRequestValidate.getKeyword()));
        return reviewRepository.findAll(spec, pageable).map(review -> reviewMapper.reviewToReviewDTO(review));
    }

    @Override
    public ReviewDTO getReviewById(Integer id) {
        Review review= reviewRepository.findById(id).orElseThrow(()->new NotFoundIdExceptionHandler("Không tìm thấy id review"));
        return reviewMapper.reviewToReviewDTO(review);
    }

    @Override
    @Transactional
    public ReviewDTO saveReview(SaveUpdateReviewRequest saveUpdateReviewRequest) {
        User user = userRepository.findById(saveUpdateReviewRequest.getUserId())
                .orElseThrow(()->new NotFoundIdExceptionHandler("Không tìm thấy id user"));
        Company company = companyRepository.findById(saveUpdateReviewRequest.getCompanyId())
                .orElseThrow(()->new NotFoundIdExceptionHandler("Không tìm thấy id company"));
        Review review = reviewMapper.saveReviewMapper(user,company,saveUpdateReviewRequest);
        return reviewMapper.reviewToReviewDTO(reviewRepository.save(review));
    }

    @Override
    public ReviewDTO updateReview(Integer reviewId, SaveUpdateReviewRequest saveUpdateReviewRequest) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()->new NotFoundIdExceptionHandler("Không tìm thấy id review"));
        User user = userRepository.findById(saveUpdateReviewRequest.getUserId())
                .orElseThrow(()->new NotFoundIdExceptionHandler("Không tìm thấy id user"));
        Company company = companyRepository.findById(saveUpdateReviewRequest.getCompanyId())
                .orElseThrow(()->new NotFoundIdExceptionHandler("Không tìm thấy id company"));

        Review mapperReview = reviewMapper.updateReviewMapper(reviewId ,user,company,saveUpdateReviewRequest);
        return reviewMapper.reviewToReviewDTO(reviewRepository.save(mapperReview));
    }

    @Override
    public ReviewDTO deleteReview(Integer reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()->new NotFoundIdExceptionHandler("Không tìm thấy id review"));
        reviewRepository.delete(review);
        return reviewMapper.reviewToReviewDTO(review);
    }
}
