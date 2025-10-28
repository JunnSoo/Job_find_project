package com.project.it_job.service.imp;

import com.project.it_job.dto.CategoryDTO;
import com.project.it_job.entity.Category;
import com.project.it_job.exception.ConflictException;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.CategoryMapper;
import com.project.it_job.repository.CategoryRepository;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.SaveUpdateCategoryRequest;
import com.project.it_job.service.CategoryService;
import com.project.it_job.specification.CategorySpecification;
import com.project.it_job.util.PageCustomHelpper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final PageCustomHelpper pageCustomHelpper;
    private final CategorySpecification categorySpecification;


    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAllByParentIdIsNull().stream().map(cate-> categoryMapper.categoryToCategoryDTO(cate)).toList();
    }

    @Override
    public Page<CategoryDTO> getAllCategoriesPage(PageRequestCustom  pageRequestCustom) {
//        validate pageCustom
        PageRequestCustom pageRequestValidate = pageCustomHelpper.validatePageCustom(pageRequestCustom);

//        Tạo page cho api
        Pageable pageable = PageRequest.of(pageRequestValidate.getPageNumber() - 1,pageRequestValidate.getPageSize());

//        Tạo search
        Specification<Category> spec = Specification.allOf(
                categorySpecification.searchByName(pageRequestValidate.getKeyword()),
                categorySpecification.parentIsNull()
        );
        return categoryRepository.findAll(spec, pageable).map( cate -> categoryMapper.categoryToCategoryDTO(cate));
    }

    @Override
    public CategoryDTO getCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(()->new NotFoundIdExceptionHandler("Không tìm thấy id category"));
        return  categoryMapper.categoryToCategoryDTO(category);
    }

    @Override
    @Transactional
    public CategoryDTO saveCategory(SaveUpdateCategoryRequest saveUpdateCategoryRequest) {
           Category categoryParent = null;
           if (saveUpdateCategoryRequest.getParentId() != null && saveUpdateCategoryRequest.getParentId() > 0){
               categoryParent = categoryRepository.findById(saveUpdateCategoryRequest.getParentId()).orElseThrow(
                       ()->new NotFoundIdExceptionHandler("Không tìm thấy id cha của category!")
               );
           }

           try {
               Category mappedCategory = categoryMapper.saveCategoryMapper(categoryParent ,saveUpdateCategoryRequest);
               return  categoryMapper.categoryToCategoryDTO(
                       categoryRepository.save(mappedCategory));
           } catch (Exception e) {
               throw new ConflictException("Lỗi thêm category");
           }
    }

    @Override
    @Transactional
    public CategoryDTO updateCategory(Integer idCate ,SaveUpdateCategoryRequest saveUpdateCategoryRequest) {
            Category categoryParent = null;
            if (saveUpdateCategoryRequest.getParentId() != null && saveUpdateCategoryRequest.getParentId() > 0){
                categoryParent = categoryRepository.findById(saveUpdateCategoryRequest.getParentId()).orElseThrow(
                        ()->new NotFoundIdExceptionHandler("Không tìm thấy id cha của category!")
                );
            }
            Category category  = categoryRepository.findById(idCate)
                    .orElseThrow(()->new NotFoundIdExceptionHandler("Không tìm thấy id category"));

            try {
                Category mapperBlog = categoryMapper.updateCategoryMapper(idCate,categoryParent ,saveUpdateCategoryRequest);
                mapperBlog.setCreatedDate(category.getCreatedDate());
                return   categoryMapper.categoryToCategoryDTO(categoryRepository.save(mapperBlog));
            } catch (Exception e) {
                throw new ConflictException("Lỗi cập nhật category!");
            }
    }

    @Override
    public CategoryDTO deleteCategoryById(Integer id) {
        Category category  = categoryRepository.findById(id)
                .orElseThrow(()->new NotFoundIdExceptionHandler("Không tìm thấy id category"));
        categoryRepository.delete(category);
        return categoryMapper.categoryToCategoryDTO(category);
    }

}
