package com.project.codinviec.controller;

import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.SaveUpdateCategoryRequest;
import com.project.codinviec.response.BaseResponse;
import com.project.codinviec.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getAllCategories(PageRequestCustom pageRequestCustom) {

        if (pageRequestCustom.getPageNumber() == 0 && pageRequestCustom.getPageSize() == 0 && pageRequestCustom.getKeyword() == null ) {
            return ResponseEntity.ok(BaseResponse.success(categoryService.getAllCategories(), "OK"));
        }
        return ResponseEntity.ok(BaseResponse.success(categoryService
                .getAllCategoriesPage(pageRequestCustom), "OK"));
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
