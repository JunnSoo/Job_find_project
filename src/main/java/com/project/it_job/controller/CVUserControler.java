package com.project.it_job.controller;

import com.project.it_job.request.CVUserRequest;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.CVUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cv-users")
public class CVUserControler {
    private final CVUserService  cvUserService;


    @GetMapping
    public ResponseEntity<?> getAll(PageRequestCustom pageRequestCustom) {

        if (pageRequestCustom.getPageNumber() == 0
                && pageRequestCustom.getPageSize() == 0
                && pageRequestCustom.getKeyword() == null
                && pageRequestCustom.getSortBy() == null
        ) {
            return ResponseEntity.ok(BaseResponse.success(cvUserService.getAll(),"OK"));
        }

        return ResponseEntity.ok(BaseResponse.success(cvUserService.getAllWithPage(pageRequestCustom),"OK"));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CVUserRequest cvUSerReqest) {
        return ResponseEntity.ok(BaseResponse.success(cvUserService.create(cvUSerReqest), "OK"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable Integer id) {
        return ResponseEntity.ok(BaseResponse.success(cvUserService.getById(id),"OK"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody CVUserRequest cvUSerReqest) {
        return ResponseEntity.ok(BaseResponse.success(cvUserService.update(id,cvUSerReqest), "OK"));
    }

    @DeleteMapping("/{id}/{candidateId}")
    public ResponseEntity<?> delete(@PathVariable Integer id,@PathVariable String candidateId) {
        return ResponseEntity.ok(BaseResponse.success(cvUserService.deleteById(id,candidateId),"OK"));
    }
}
