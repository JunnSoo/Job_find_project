package com.project.it_job.controller;

import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @GetMapping("/{keyword}")
    public ResponseEntity<?> search(@PathVariable String keyword){
        return ResponseEntity.ok(BaseResponse.success(searchService.getSearch(keyword), "OK"));
    }

}
