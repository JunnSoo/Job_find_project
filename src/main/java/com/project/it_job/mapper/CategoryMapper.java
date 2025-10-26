package com.project.it_job.mapper;

import com.project.it_job.dto.CategoryDTO;
import com.project.it_job.entity.Category;
import com.project.it_job.request.auth.SaveUpdateCategoryRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {


    public CategoryDTO categoryToCategoryDTO(Category category) {
        if (category == null) return null;

        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .parentId(category.getParent() != null ? category.getParent().getId() : 0)
                .children(toDTOList(category.getChildren())) // đệ quy
                .build();
    }
    public List<CategoryDTO> toDTOList(List<Category> categories) {
        if (categories == null || categories.isEmpty()) return List.of();
        return categories.stream()
                .map(category -> categoryToCategoryDTO(category)).toList();
    }

    public Category saveCategoryMapper(Category categoryParent,  SaveUpdateCategoryRequest saveUpdateCategoryRequest){
        if (saveUpdateCategoryRequest == null) return null;
        return Category.builder()
                .name(saveUpdateCategoryRequest.getName())
                .parent(categoryParent)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
    }

    public Category updateCategoryMapper(int idCate,Category categoryParent,  SaveUpdateCategoryRequest saveUpdateCategoryRequest){
        if (saveUpdateCategoryRequest == null) return null;
        return Category.builder()
                .id(idCate)
                .name(saveUpdateCategoryRequest.getName())
                .parent(categoryParent)
                .updatedDate(LocalDateTime.now())
                .build();
    }


}
