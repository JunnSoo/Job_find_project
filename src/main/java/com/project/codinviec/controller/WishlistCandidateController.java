package com.project.codinviec.controller;

import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.WishlistCandidateRequest;
import com.project.codinviec.response.BaseResponse;
import com.project.codinviec.service.WishlistCandidateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/wishlist-candidate")
public class WishlistCandidateController {
    private final WishlistCandidateService wishlistCandidateService;

    @GetMapping
    public ResponseEntity<?> getAllWishlistCandidates(PageRequestCustom pageRequestCustom) {
        if (pageRequestCustom.getPageNumber() == 0 && pageRequestCustom.getPageSize() == 0 ) {
            return ResponseEntity.ok(BaseResponse.success(wishlistCandidateService.getAllWishlistCandidates(),"OK"));
        }
        return ResponseEntity.ok(BaseResponse.success(wishlistCandidateService.getAllWishlistCandidatesPage(pageRequestCustom),"OK"));
    }

    @GetMapping("/{idHr}")
    public ResponseEntity<?> getWishlistCandidateByHrId(@PathVariable String idHr) {
        return ResponseEntity.ok(BaseResponse.success(wishlistCandidateService.getWishlistCandidateByHrId(idHr),"OK"));
    }

    @PostMapping
    public ResponseEntity<?> saveWishlistCandidate(@Valid @RequestBody WishlistCandidateRequest wishlistCandidateRequest){
        return ResponseEntity.ok(BaseResponse.success(wishlistCandidateService.saveWishlistCandidate(wishlistCandidateRequest),"OK"));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteWishlistCandidate( @Valid @RequestBody WishlistCandidateRequest wishlistCandidateRequest){
        return ResponseEntity.ok(BaseResponse.success(wishlistCandidateService.deleteWistListCandidate(wishlistCandidateRequest),"OK"));
    }
}
