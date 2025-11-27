package com.project.codinviec.controller;

import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.SaveUpdateReviewRequest;
import com.project.codinviec.response.BaseResponse;
import com.project.codinviec.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<?> getAllReviews(PageRequestCustom pageRequestCustom){
        if (pageRequestCustom.getPageNumber() == 0 && pageRequestCustom.getPageSize() == 0  && pageRequestCustom.getKeyword() == null ) {
            return ResponseEntity.ok(BaseResponse.success(reviewService.getAllReviews(),"OK"));
        }
        return ResponseEntity.ok(BaseResponse.success(reviewService.getAllReviewsPage(pageRequestCustom),"OK"));
    }

    @GetMapping("/{idReview}")
    public ResponseEntity<?> getReviewById(@PathVariable("idReview") Integer idReview){
         return ResponseEntity.ok(BaseResponse.success(reviewService.getReviewById(idReview),"OK"));
    }

    @GetMapping("/user/{idUser}")
    public ResponseEntity<?> getReviewByUserId(@PathVariable("idUser") String idUser){
        return ResponseEntity.ok(BaseResponse.success(reviewService.getReviewsByUserId(idUser),"OK"));
    }

    @GetMapping("/company/{idCompany}")
    public ResponseEntity<?> getReviewByCompanyId(@PathVariable("idCompany") String idCompany){
        return ResponseEntity.ok(BaseResponse.success(reviewService.getReviewsByCompanyId(idCompany),"OK"));
    }

    @PostMapping
    public ResponseEntity<?> saveReview(@Valid @RequestBody SaveUpdateReviewRequest saveUpdateReviewRequest){
        return ResponseEntity.ok(BaseResponse.success(reviewService.saveReview(saveUpdateReviewRequest), "OK"));
    }

    @PutMapping("/{idReview}")
    public ResponseEntity<?> updateReview (@PathVariable Integer idReview ,@Valid @RequestBody SaveUpdateReviewRequest saveUpdateReviewRequest){
        return ResponseEntity.ok(BaseResponse.success(reviewService.updateReview(idReview ,saveUpdateReviewRequest), "OK"));
    }

    @DeleteMapping("/{idReview}")
    public ResponseEntity<?> deleteReview(@PathVariable Integer idReview){
        return ResponseEntity.ok(BaseResponse.success(reviewService.deleteReview(idReview), "OK"));
    }
}
