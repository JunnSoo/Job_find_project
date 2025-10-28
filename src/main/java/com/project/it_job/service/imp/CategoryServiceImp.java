package com.project.it_job.service.imp;

import com.project.it_job.dto.CategoryDTO;
import com.project.it_job.entity.Category;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.exception.ParamExceptionHandler;
import com.project.it_job.mapper.CategoryMapper;
import com.project.it_job.repository.CategoryRepository;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.SaveUpdateCategoryRequest;
import com.project.it_job.service.CategoryService;
import com.project.it_job.specification.CategorySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
        return categoryRepository.findAllByParentIdIsNull().stream().map(cate-> categoryMapper.categoryToCategoryDTO(cate)).toList();
    }

    @Override
    public Page<CategoryDTO> getAllCategoriesPage(PageRequestCustom  pageRequestCustom) {
        //       Trường hợp keyword = null
        if (pageRequestCustom.getKeyword() == null) {
            pageRequestCustom.setKeyword("");
        }else{
//            set pageSize mặc định khi có keyword và pageSize không được truyền
            if (pageRequestCustom.getPageSize() == 0) {
                pageRequestCustom.setPageSize(5);            }
        }

//        truyền pageSize không hợp lệ ( > 0 mới tính)
        if (pageRequestCustom.getPageSize() <= 0)
            throw new ParamExceptionHandler("Truyền pageSize không hợp lệ!");

        Pageable pageable = PageRequest.of(pageRequestCustom.getPageNumber(), pageRequestCustom.getPageSize());
        Specification<Category> spec = Specification.allOf(
                CategorySpecification.searchByName(pageRequestCustom.getKeyword()),
                CategorySpecification.parentIsNull()
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
    public CategoryDTO saveCategory(SaveUpdateCategoryRequest saveUpdateCategoryRequest) {
        Category categoryParent = null;
        if (saveUpdateCategoryRequest.getParentId() != null && saveUpdateCategoryRequest.getParentId() > 0){
            categoryParent = categoryRepository.findById(saveUpdateCategoryRequest.getParentId()).orElseThrow(
                    ()->new NotFoundIdExceptionHandler("Không tìm thấy id cha của category!")
            );
        }

        return  categoryMapper.categoryToCategoryDTO(
                categoryRepository.save(categoryMapper.saveCategoryMapper(categoryParent ,saveUpdateCategoryRequest)));
    }

    @Override
    public CategoryDTO updateCategory(int idCate ,SaveUpdateCategoryRequest saveUpdateCategoryRequest) {
        Category categoryParent = null;
        if (saveUpdateCategoryRequest.getParentId() != null && saveUpdateCategoryRequest.getParentId() > 0){
            categoryParent = categoryRepository.findById(saveUpdateCategoryRequest.getParentId()).orElseThrow(
                    ()->new NotFoundIdExceptionHandler("Không tìm thấy id cha của category!")
            );
        }
        Category category  = categoryRepository.findById(idCate)
                .orElseThrow(()->new NotFoundIdExceptionHandler("Không tìm thấy id category"));

        Category mapperBlog = categoryMapper.updateCategoryMapper(idCate,categoryParent ,saveUpdateCategoryRequest);
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
