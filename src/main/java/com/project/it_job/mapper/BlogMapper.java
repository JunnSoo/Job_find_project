package com.project.it_job.mapper;

import com.project.it_job.dto.BlogDTO;
import com.project.it_job.entity.Blog;
import com.project.it_job.request.SaveBlogRequest;
import com.project.it_job.request.UpdateBlogRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BlogMapper {
    public BlogDTO blogToDTO(Blog blog) {
        BlogDTO blogDTO = new BlogDTO();
        blogDTO.setId(blog.getId());
        blogDTO.setTitle(blog.getTitle());
        blogDTO.setPicture(blog.getPicture());
        blogDTO.setShortDescription(blog.getShortDescription());
        blogDTO.setCreatedDate(blog.getCreatedDate());
        blogDTO.setUpdatedDate(blog.getUpdatedDate());
        return blogDTO;
    }

    public Blog saveBlogToBlog(SaveBlogRequest saveBlogRequest) {
        Blog blog = new Blog();
        blog.setTitle(saveBlogRequest.getTitle());
        blog.setPicture(saveBlogRequest.getPicture());
        blog.setShortDescription(saveBlogRequest.getShortDescription());
        blog.setCreatedDate(LocalDateTime.now());
        blog.setUpdatedDate(LocalDateTime.now());
        return blog;
    }

    public Blog updateBlogToBlog(UpdateBlogRequest updateBlogRequest) {
        Blog blog = new Blog();
        blog.setId(updateBlogRequest.getId());
        blog.setTitle(updateBlogRequest.getTitle());
        blog.setPicture(updateBlogRequest.getPicture());
        blog.setShortDescription(updateBlogRequest.getShortDescription());
        blog.setUpdatedDate(LocalDateTime.now());
        return blog;
    }
}
