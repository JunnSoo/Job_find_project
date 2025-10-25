package com.project.it_job.service.imp;

import com.project.it_job.dto.BlogDTO;
import com.project.it_job.entity.Blog;
import com.project.it_job.exception.DeleteExceptionHandler;
import com.project.it_job.exception.GetExceptionHandler;
import com.project.it_job.exception.UpdateExceptionHandler;
import com.project.it_job.mapper.BlogMapper;
import com.project.it_job.repository.BlogRepository;
import com.project.it_job.request.GetBlogRequest;
import com.project.it_job.request.SaveBlogRequest;
import com.project.it_job.request.UpdateBlogRequest;
import com.project.it_job.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImp implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private BlogMapper blogMapper;

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
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new GetExceptionHandler());
        return  blogMapper.blogToDTO(blog);
    }

    @Override
    public BlogDTO saveBlog(SaveBlogRequest saveBlogRequest) {
        return  blogMapper.blogToDTO(blogRepository.save(blogMapper.saveBlogToBlog(saveBlogRequest)));
    }

    @Override
    public BlogDTO updateBlogById(UpdateBlogRequest updateBlogRequest) {
        Blog blog = blogRepository.findById(updateBlogRequest.getId()).orElseThrow(() -> new UpdateExceptionHandler());
        return  blogMapper.blogToDTO(blogRepository.save(blogMapper.updateBlogToBlog(updateBlogRequest)));
    }

    @Override
    public BlogDTO deleteBlogById(int id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new DeleteExceptionHandler());
        blogRepository.delete(blog);
        return blogMapper.blogToDTO(blog);
    }


}
