package com.project.it_job.controller;

import com.project.it_job.request.GetCategoryRequest;
import com.project.it_job.request.SaveCategoryRequest;
import com.project.it_job.request.UpdateCategoryRequest;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getAllCategories(GetCategoryRequest getCategoryRequest) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(HttpStatus.OK.value());
        baseResponse.setMessage("OK");
        if (getCategoryRequest.getPageNumber() <= 0 || getCategoryRequest.getPageSize() <= 0) {
            baseResponse.setData(categoryService.getAllCategories());
        }else{
            baseResponse.setData(categoryService.getAllCategoriesPage(getCategoryRequest));
        }
        return ResponseEntity.ok(baseResponse);
    }

    @GetMapping("/{idCate}")
    public ResponseEntity<?> getCategoryById(@PathVariable("idCate") Integer idCate) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(HttpStatus.OK.value());
        baseResponse.setMessage("OK");
        baseResponse.setData(categoryService.getCategoryById(idCate));
        return ResponseEntity.ok(baseResponse);
    }

    @PostMapping
    public ResponseEntity<?> saveCategory(@Valid @RequestBody SaveCategoryRequest saveCategoryRequest) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(HttpStatus.OK.value());
        baseResponse.setMessage("OK");
        baseResponse.setData(categoryService.saveCategory(saveCategoryRequest));
        return ResponseEntity.ok(baseResponse);
    }

    @PutMapping
    public ResponseEntity<?> updateCategory(@Valid @RequestBody UpdateCategoryRequest updateCategoryRequest) {
       BaseResponse  baseResponse = new BaseResponse();
       baseResponse.setCode(HttpStatus.OK.value());
        baseResponse.setMessage("OK");
        baseResponse.setData(categoryService.updateCategory(updateCategoryRequest));
        return ResponseEntity.ok(baseResponse);
    }

    @DeleteMapping("/{idCate}")
    public ResponseEntity<?> deleteCategory(@PathVariable("idCate") Integer idCate) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(HttpStatus.OK.value());
        baseResponse.setMessage("OK");
        baseResponse.setData(categoryService.deleteCategoryById(idCate));
        return ResponseEntity.ok(baseResponse);
    }
}
