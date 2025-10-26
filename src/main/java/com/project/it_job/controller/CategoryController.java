package com.project.it_job.controller;

import com.project.it_job.request.GetCategoryRequest;
import com.project.it_job.request.auth.SaveUpdateCategoryRequest;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getAllCategories(GetCategoryRequest getCategoryRequest) {

        if (getCategoryRequest.getPageNumber() <= 0 || getCategoryRequest.getPageSize() <= 0) {
            return ResponseEntity.ok(BaseResponse.success(categoryService.getAllCategories(), "OK"));
        }
        return ResponseEntity.ok(BaseResponse.success(categoryService
                .getAllCategoriesPage(getCategoryRequest), "OK"));
    }

    @GetMapping("/{idCate}")
    public ResponseEntity<?> getCategoryById(@PathVariable("idCate") Integer idCate) {
        return ResponseEntity.ok(BaseResponse.success(categoryService.getCategoryById(idCate), "OK"));
    }

    @PostMapping
    public ResponseEntity<?> saveCategory(@Valid @RequestBody SaveUpdateCategoryRequest saveUpdateCategoryRequest) {
        return ResponseEntity.ok(BaseResponse.success(categoryService.saveCategory(saveUpdateCategoryRequest), "OK"));

    }

    @PutMapping("/{idCate}")
    public ResponseEntity<?> updateCategory(@PathVariable int idCate ,@Valid @RequestBody SaveUpdateCategoryRequest saveUpdateCategoryRequest) {
        return ResponseEntity.ok(BaseResponse.success(categoryService.updateCategory(idCate, saveUpdateCategoryRequest), "OK"));

    }

    @DeleteMapping("/{idCate}")
    public ResponseEntity<?> deleteCategory(@PathVariable("idCate") Integer idCate) {
        return ResponseEntity.ok(BaseResponse.success(categoryService.deleteCategoryById(idCate), "OK"));

    }
}
