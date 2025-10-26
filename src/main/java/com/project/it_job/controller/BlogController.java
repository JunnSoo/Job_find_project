package com.project.it_job.controller;

import com.project.it_job.request.GetBlogRequest;
import com.project.it_job.request.SaveUpdateBlogRequest;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping
    public ResponseEntity<?> getAllBlog(GetBlogRequest getBlogRequest){
        if (getBlogRequest.getPageNumber() <= 0 || getBlogRequest.getPageSize() <= 0) {
            return ResponseEntity.ok(BaseResponse.success(blogService.getAllBlog(),"OK"));
        }
        return ResponseEntity.ok(BaseResponse.success(blogService.getAllBlogPage(getBlogRequest),"OK"));
    }

    @GetMapping("/{idBlog}")
    public ResponseEntity<?> getBlogById(@PathVariable("idBlog") int idBlog){
        return ResponseEntity.ok(BaseResponse.success(blogService.getBlogById(idBlog),"OK"));
    }

    @PostMapping
    public ResponseEntity<?> saveBlog(@Valid @RequestBody SaveUpdateBlogRequest saveUpdateBlogRequest){
        return ResponseEntity.ok(BaseResponse.success(blogService.saveBlog(saveUpdateBlogRequest),"OK"));
    }

    @PutMapping("/{idBlog}")
    public ResponseEntity<?> updateBlog(@PathVariable int idBlog ,@Valid @RequestBody SaveUpdateBlogRequest saveUpdateBlogRequest){
        return ResponseEntity.ok(BaseResponse.success(blogService.updateBlogById(idBlog, saveUpdateBlogRequest),"OK"));
    }

    @DeleteMapping("{idBlog}")
    public ResponseEntity<?> deleteBlog(@PathVariable int idBlog){
        return ResponseEntity.ok(BaseResponse.success(blogService.deleteBlogById(idBlog),"OK"));

    }




}
