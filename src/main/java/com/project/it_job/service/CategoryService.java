package com.project.it_job.service;

import com.project.it_job.dto.CategoryDTO;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.SaveUpdateCategoryRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();
    Page<CategoryDTO> getAllCategoriesPage(PageRequestCustom pageRequestCustom);
    CategoryDTO getCategoryById(Integer id);
    CategoryDTO saveCategory(SaveUpdateCategoryRequest saveUpdateCategoryRequest);
    CategoryDTO updateCategory(Integer idCate, SaveUpdateCategoryRequest saveUpdateCategoryRequest);
    CategoryDTO deleteCategoryById(Integer id);
}
