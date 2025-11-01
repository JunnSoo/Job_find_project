package com.project.it_job.controller;

import com.project.it_job.request.AwardRequest;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.AwardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/award")
@RequiredArgsConstructor
public class AwardController {

    private final AwardService awardService;

    @GetMapping
    public ResponseEntity<?> getAllAward(PageRequestCustom pageRequestCustom) {
        if (pageRequestCustom.getPageNumber() == 0
                && pageRequestCustom.getPageSize() == 0
                && pageRequestCustom.getKeyword() == null) {
            return ResponseEntity.ok(BaseResponse.success(awardService.getAllAward(), "ok"));
        }
        return ResponseEntity.ok(BaseResponse.success(awardService.getAllAwardPage(pageRequestCustom), "ok"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAwardById(@PathVariable Integer id) {
        return ResponseEntity.ok(BaseResponse.success(awardService.getAwardById(id), "ok"));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAwardByUser(@PathVariable String userId) {
        return ResponseEntity.ok(BaseResponse.success(awardService.getAwardByUser(userId), "ok"));
    }

    @PostMapping
    public ResponseEntity<?> createAward(@RequestBody AwardRequest request) {
        return ResponseEntity.ok(BaseResponse.success(awardService.createAward(request), "ok"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAward(@PathVariable Integer id,
                                         @RequestBody AwardRequest request) {
        return ResponseEntity.ok(BaseResponse.success(awardService.updateAward(id, request), "ok"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAward(@PathVariable Integer id) {
        return ResponseEntity.ok(BaseResponse.success(awardService.deleteAward(id), "ok"));
    }
}
