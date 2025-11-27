package com.project.codinviec.service;

import com.project.codinviec.dto.CategoryDTO;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.SaveUpdateCategoryRequest;
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
