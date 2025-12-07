package com.project.codinviec.controller;

import com.project.codinviec.response.BaseResponse;
import com.project.codinviec.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @GetMapping("/{keyword}")
    public ResponseEntity<?> search(@PathVariable String keyword,
                                    @RequestParam(defaultValue = "0") int provinceId) {
        return ResponseEntity.ok(BaseResponse.success(searchService.getSearch(keyword, provinceId), "OK"));
    }

}
