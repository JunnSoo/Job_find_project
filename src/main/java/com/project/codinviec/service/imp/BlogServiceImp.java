package com.project.codinviec.service.imp;

import com.project.codinviec.dto.BlogDTO;
import com.project.codinviec.dto.BlogDetailDTO;
import com.project.codinviec.entity.Blog;
import com.project.codinviec.exception.common.ConflictExceptionHandler;
import com.project.codinviec.exception.common.NotFoundIdExceptionHandler;
import com.project.codinviec.mapper.BlogMapper;
import com.project.codinviec.repository.BlogRepository;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.SaveUpdateBlogRequest;
import com.project.codinviec.service.BlogService;
import com.project.codinviec.specification.BlogSpecification;
import com.project.codinviec.util.helper.PageCustomHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogServiceImp implements BlogService {

    private final BlogRepository blogRepository;
    private final BlogMapper blogMapper;
    private final PageCustomHelper pageCustomHelper;
    private final BlogSpecification blogSpecification;

    @Override
    public List<BlogDTO> getAllBlog() {
        return blogRepository.findAll().stream()
                .map(blogMapper::blogToDTO)
                .toList();
    }

    @Override
    public Page<BlogDTO> getAllBlogPage(PageRequestCustom pageRequestCustom) {
        // Validate pageCustom
        PageRequestCustom pageRequestValidate = pageCustomHelper.validatePageCustom(pageRequestCustom);

        Sort sort = switch (pageRequestValidate.getSortBy()) {
            case "createdDateAsc" -> Sort.by(Sort.Direction.ASC, "createdDate");
            case "createdDateDesc" -> Sort.by(Sort.Direction.DESC, "createdDate");
            case "softHighlight" -> Sort.by(Sort.Direction.ASC, "isHighLight");
            default -> Sort.by(Sort.Direction.DESC, "createdDate");
        };
        // Tạo page cho api
        Pageable pageable = PageRequest.of(pageRequestValidate.getPageNumber() - 1, pageRequestValidate.getPageSize(),sort);
        // Tạo search
        Specification<Blog> spec = Specification
                .allOf(blogSpecification.searchByName(pageRequestValidate.getKeyword()));
        return blogRepository.findAll(spec, pageable)
                .map(blogMapper::blogToDTO);
    }

    @Override
    public BlogDTO getBlogById(Integer id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy user ID"));
        return blogMapper.blogToDTO(blog);
    }

    @Override
    @Transactional
    public BlogDTO saveBlog(SaveUpdateBlogRequest saveUpdateBlogRequest) {
        try {
            Blog blog = blogMapper.saveBlogMapper(saveUpdateBlogRequest);
            return blogMapper.blogToDTO(blogRepository.save(blog));
        } catch (Exception e) {
            throw new ConflictExceptionHandler("Lỗi thêm blog!");
        }
    }

    @Override
    @Transactional
    public BlogDTO updateBlogById(Integer idBlog, SaveUpdateBlogRequest saveUpdateBlogRequest) {
        Blog blog = blogRepository.findById(idBlog)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy user ID"));

        try {
            Blog mappedBlog = blogMapper.updateBlogMapper(idBlog, saveUpdateBlogRequest);
            mappedBlog.setCreatedDate(blog.getCreatedDate());
            return blogMapper.blogToDTO(blogRepository.save(mappedBlog));
        } catch (Exception e) {
            throw new ConflictExceptionHandler("Lỗi cập nhật blog!");
        }
    }

    @Override
    @Transactional
    public BlogDTO deleteBlogById(Integer id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy user ID"));
        blogRepository.delete(blog);
        return blogMapper.blogToDTO(blog);
    }

    @Override
    public BlogDetailDTO getBlogDetailById(Integer id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy user ID"));
        return blogMapper.blogToBlogDetailDTO(blog);
    }
}
