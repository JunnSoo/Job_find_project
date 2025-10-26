package com.project.it_job.service.imp;

import com.project.it_job.dto.BlogDTO;
import com.project.it_job.dto.BlogDetailDTO;
import com.project.it_job.entity.Blog;
import com.project.it_job.entity.BlogDetail;
import com.project.it_job.exception.ConflictException;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.BlogDetailMapper;
import com.project.it_job.mapper.BlogMapper;
import com.project.it_job.repository.BlogRepository;
import com.project.it_job.request.GetBlogRequest;
import com.project.it_job.request.SaveBlogRequest;
import com.project.it_job.request.SaveUpdateBlogRequest;
import com.project.it_job.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BlogServiceImp implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private BlogDetailMapper blogDetailMapper;


    @Override
    public List<BlogDTO> getAllBlog() {
        return blogRepository.findAll().stream().map(blog -> blogMapper.blogToDTO(blog)).toList();
    }

    @Override
    public Page<BlogDTO> getAllBlogPage(GetBlogRequest getBlogRequest) {

        Pageable pageable = PageRequest.of(getBlogRequest.getPageNumber(), getBlogRequest.getPageSize());
        return blogRepository.findAll(pageable).map( blog -> blogMapper.blogToDTO(blog));
    }

    @Override
    public BlogDTO getBlogById(int id) {
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
    public BlogDTO updateBlogById(int idBlog, SaveUpdateBlogRequest saveUpdateBlogRequest) {
        Blog blog = blogRepository.findById(idBlog).orElseThrow(()
                -> new NotFoundIdExceptionHandler("Không tìm thấy user ID"));
        Blog mappedBlog = blogMapper.updateBlogMapper(idBlog,saveUpdateBlogRequest);
        mappedBlog.setCreatedDate(blog.getCreatedDate());
        return  blogMapper.blogToDTO(blogRepository.save(mappedBlog));
    }

    @Override
    public BlogDTO deleteBlogById(int id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy user ID"));
        blogRepository.delete(blog);
        return blogMapper.blogToDTO(blog);
    }

    @Override
    public BlogDetailDTO getBlogDetailById(int id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy user ID"));
        return blogDetailMapper.blogToBlogDetailDTO(blog);
    }


}