package com.project.it_job.service.imp;

import com.project.it_job.dto.BlogDTO;
import com.project.it_job.dto.BlogDetailDTO;
import com.project.it_job.entity.Blog;
import com.project.it_job.exception.ConflictException;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.BlogMapper;
import com.project.it_job.repository.BlogRepository;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.SaveUpdateBlogRequest;
import com.project.it_job.service.BlogService;
import com.project.it_job.specification.BlogSpecification;
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
public class BlogServiceImp implements BlogService {

    private final BlogRepository blogRepository;
    private final BlogMapper blogMapper;
    private final PageCustomHelpper pageCustomHelpper;
    private final BlogSpecification blogSpecification;




    @Override
    public List<BlogDTO> getAllBlog() {
        return blogRepository.findAll().stream().map(blog -> blogMapper.blogToDTO(blog)).toList();
    }



    @Override
    public Page<BlogDTO> getAllBlogPage(PageRequestCustom pageRequestCustom) {
//        validate pageCustom
        PageRequestCustom pageRequestValidate = pageCustomHelpper.validatePageCustom(pageRequestCustom);

//        Tạo page cho api
        Pageable pageable = PageRequest.of(pageRequestValidate.getPageNumber(),pageRequestValidate.getPageSize());

//        Tạo search
        Specification<Blog> spec = Specification.allOf(blogSpecification.searchByName(pageRequestValidate.getKeyword()));
        return blogRepository.findAll(spec, pageable).map( blog -> blogMapper.blogToDTO(blog));
    }


    @Override
    public BlogDTO getBlogById(Integer id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy user ID"));
        return  blogMapper.blogToDTO(blog);
    }

    @Override
    @Transactional
    public BlogDTO saveBlog(SaveUpdateBlogRequest saveUpdateBlogRequest) {
       try{
           Blog blog = blogMapper.saveBlogMapper(saveUpdateBlogRequest);
           return  blogMapper.blogToDTO(blogRepository.save(blog));
       }catch (Exception e){
            throw new ConflictException("Lỗi thêm blog!");
       }
    }

    @Override
    @Transactional
    public BlogDTO updateBlogById(Integer idBlog, SaveUpdateBlogRequest saveUpdateBlogRequest) {
           Blog blog = blogRepository.findById(idBlog).orElseThrow(()
                   -> new NotFoundIdExceptionHandler("Không tìm thấy user ID"));

          try {
              Blog mappedBlog = blogMapper.updateBlogMapper(idBlog,saveUpdateBlogRequest);
              mappedBlog.setCreatedDate(blog.getCreatedDate());
              return  blogMapper.blogToDTO(blogRepository.save(mappedBlog));
          } catch (Exception e) {
                throw new ConflictException("Lỗi cập nhật blog!");
          }
    }

    @Override
    public BlogDTO deleteBlogById(Integer id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy user ID"));
        blogRepository.delete(blog);
        return blogMapper.blogToDTO(blog);
    }

    @Override
    public BlogDetailDTO getBlogDetailById(Integer id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy user ID"));
        return blogMapper.blogToBlogDetailDTO(blog);
    }
}