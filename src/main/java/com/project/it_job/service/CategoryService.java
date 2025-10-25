package com.project.it_job.service;

import com.project.it_job.dto.CategoryDTO;
import com.project.it_job.request.GetCategoryRequest;
import com.project.it_job.request.SaveCategoryRequest;
import com.project.it_job.request.UpdateCategoryRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();
    Page<CategoryDTO> getAllCategoriesPage(GetCategoryRequest getCategoryRequest);
    CategoryDTO getCategoryById(Integer id);
    CategoryDTO saveCategory(SaveCategoryRequest saveCategoryRequest);
    CategoryDTO updateCategory(UpdateCategoryRequest updateCategoryRequest);
    CategoryDTO deleteCategoryById(Integer id);
}
