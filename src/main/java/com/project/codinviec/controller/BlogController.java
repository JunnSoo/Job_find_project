package com.project.codinviec.controller;

import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.SaveUpdateBlogRequest;
import com.project.codinviec.response.BaseResponse;
import com.project.codinviec.service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping
    public ResponseEntity<?> getAllBlog(PageRequestCustom pageRequestCustom){
        if (pageRequestCustom.getPageNumber() == 0 && pageRequestCustom.getPageSize() == 0  && pageRequestCustom.getKeyword() == null ) {
            return ResponseEntity.ok(BaseResponse.success(blogService.getAllBlog(),"OK"));
        }
        return ResponseEntity.ok(BaseResponse.success(blogService.getAllBlogPage(pageRequestCustom),"OK"));
    }

    @GetMapping("/{idBlog}/detail")
    public ResponseEntity<?> getDetailBlogById(@PathVariable("idBlog") int idBlog){
        return ResponseEntity.ok(BaseResponse.success(blogService.getBlogDetailById(idBlog),"OK"));
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
