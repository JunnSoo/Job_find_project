package com.project.it_job.controller;

import com.project.it_job.request.GetBlogRequest;
import com.project.it_job.request.SaveBlogRequest;
import com.project.it_job.request.UpdateBlogRequest;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping
    public ResponseEntity<?> getAllBlog(GetBlogRequest getBlogRequest){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("OK");
        baseResponse.setCode(200);
        if (getBlogRequest.getPageNumber() <= 0 || getBlogRequest.getPageSize() <= 0) {
            baseResponse.setData(blogService.getAllBlog());
        }else{
            baseResponse.setData(blogService.getAllBlogPage(getBlogRequest));
        }
        return ResponseEntity.ok(baseResponse);
    }

    @PostMapping
    public ResponseEntity<?> saveBlog(@Valid @RequestBody SaveBlogRequest saveBlogRequest){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("OK");
        baseResponse.setCode(200);
        baseResponse.setData(blogService.saveBlog(saveBlogRequest));
        return ResponseEntity.ok(baseResponse);
    }

    @PutMapping
    public ResponseEntity<?> updateBlog(@Valid @RequestBody UpdateBlogRequest updateBlogRequest){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("OK");
        baseResponse.setCode(200);
        baseResponse.setData(blogService.updateBlogById(updateBlogRequest));
        return ResponseEntity.ok(baseResponse);
    }

    @DeleteMapping("{idBlog}")
    public ResponseEntity<?> deleteBlog(@PathVariable int idBlog){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("OK");
        baseResponse.setCode(200);
        baseResponse.setData(blogService.deleteBlogById(idBlog));
        return ResponseEntity.ok(baseResponse);
    }




}
