package com.project.codinviec.controller;

import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.WishlistJobRequest;
import com.project.codinviec.response.BaseResponse;
import com.project.codinviec.service.WishlistJobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishlist-job")
@RequiredArgsConstructor
public class WishlistJobController {
    private final WishlistJobService wishlistJobService;

    @GetMapping
    public ResponseEntity<?> getAllWishlistJob(PageRequestCustom pageRequestCustom) {
        if (pageRequestCustom.getPageNumber() == 0 && pageRequestCustom.getPageSize() == 0 ) {
            return ResponseEntity.ok(BaseResponse.success(wishlistJobService.getAllWishlistJobs(),"OK"));
        }
        return ResponseEntity.ok(BaseResponse.success(wishlistJobService.getAllWishlistJobsPage(pageRequestCustom),"OK"));
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<?> getWishlistJobByUserId(@PathVariable String idUser) {
        return ResponseEntity.ok(BaseResponse.success(wishlistJobService.getWishlistJobsByUserId(idUser),"OK"));
    }

    @PostMapping
    public ResponseEntity<?> saveWishlistJob(@Valid @RequestBody WishlistJobRequest wishlistJobRequest) {
        return ResponseEntity.ok(BaseResponse.success(wishlistJobService.saveWishlistJobs(wishlistJobRequest),"OK"));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteWishlistJobById(@Valid @RequestBody WishlistJobRequest wishlistJobRequest) {
        return ResponseEntity.ok(BaseResponse.success(wishlistJobService.deleteWishlistJobs(wishlistJobRequest),"OK"));
    }

}
