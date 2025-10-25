package com.project.it_job.service.imp;

import com.project.it_job.dto.CategoryDTO;
import com.project.it_job.entity.Category;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.CategoryMapper;
import com.project.it_job.repository.CategoryRepository;
import com.project.it_job.request.GetCategoryRequest;
import com.project.it_job.request.SaveCategoryRequest;
import com.project.it_job.request.UpdateCategoryRequest;
import com.project.it_job.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(cate-> categoryMapper.categoryToCategoryDTO(cate)).toList();
    }

    @Override
    public Page<CategoryDTO> getAllCategoriesPage(GetCategoryRequest getCategoryRequest) {
        Pageable pageable =  PageRequest.of(getCategoryRequest.getPageNumber(),getCategoryRequest.getPageSize());
        return categoryRepository.findAll(pageable).map( category -> categoryMapper.categoryToCategoryDTO(category));
    }

    @Override
    public CategoryDTO getCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(()->new NotFoundIdExceptionHandler("Không tìm thấy id category"));
        return  categoryMapper.categoryToCategoryDTO(category);
    }

    @Override
    public CategoryDTO saveCategory(SaveCategoryRequest saveCategoryRequest) {
        return  categoryMapper.categoryToCategoryDTO(
                categoryRepository.save(categoryMapper.saveCategoryToCategory(saveCategoryRequest)));
    }

    @Override
    public CategoryDTO updateCategory(UpdateCategoryRequest updateCategoryRequest) {
        Category category  = categoryRepository.findById(updateCategoryRequest.getId())
                .orElseThrow(()->new NotFoundIdExceptionHandler("Không tìm thấy id category"));

        Category mapperBlog = categoryMapper.updateCategoryToCategory(updateCategoryRequest);
        mapperBlog.setCreatedDate(category.getCreatedDate());

        return   categoryMapper.categoryToCategoryDTO(categoryRepository.save(mapperBlog));
    }

    @Override
    public CategoryDTO deleteCategoryById(Integer id) {
        Category category  = categoryRepository.findById(id)
                .orElseThrow(()->new NotFoundIdExceptionHandler("Không tìm thấy id category"));
        categoryRepository.delete(category);
        return categoryMapper.categoryToCategoryDTO(category);
    }

}
