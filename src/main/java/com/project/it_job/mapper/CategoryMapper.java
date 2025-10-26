package com.project.it_job.mapper;

import com.project.it_job.dto.CategoryDTO;
import com.project.it_job.entity.Category;
import com.project.it_job.request.UpdateCategoryRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CategoryMapper {
    public CategoryDTO categoryToCategoryDTO(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setParentId(category.getParentId());
        categoryDTO.setCreatedDate(category.getCreatedDate());
        categoryDTO.setUpdatedDate(category.getUpdatedDate());
        return categoryDTO;
    }

//    public Category saveCategoryToCategory(SaveCategoryRequest saveCategoryRequest){
//        Category category = new Category();
//        category.setName(saveCategoryRequest.getName());
//        if (saveCategoryRequest.getParentId() != null){
//            category.setParentId(saveCategoryRequest.getParentId());
//        }
//        category.setCreatedDate(LocalDateTime.now());
//        category.setUpdatedDate(LocalDateTime.now());
//        return category;
//    }

    public Category updateCategoryToCategory(UpdateCategoryRequest updateCategoryRequest){
        Category category = new Category();
        category.setId(updateCategoryRequest.getId());
        category.setName(updateCategoryRequest.getName());
        category.setParentId(updateCategoryRequest.getParentId());
        category.setUpdatedDate(LocalDateTime.now());
        return category;
    }


}
