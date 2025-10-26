package com.project.it_job.mapper;

import com.project.it_job.dto.BlogDTO;
import com.project.it_job.entity.Blog;
import com.project.it_job.entity.BlogDetail;
import com.project.it_job.request.SaveUpdateBlogRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BlogMapper {
    public BlogDTO blogToDTO(Blog blog) {
        if (blog == null) return null;
        return BlogDTO.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .picture(blog.getPicture())
                .shortDescription(blog.getShortDescription())
                .createdDate(blog.getCreatedDate())
                .updatedDate(blog.getUpdatedDate())
                .build();
    }

    public Blog saveBlogMapper(SaveUpdateBlogRequest  saveUpdateBlogRequest) {
        if (saveUpdateBlogRequest == null) return null;
            Blog blog = Blog.builder()
                    .title(saveUpdateBlogRequest.getTitle())
                    .picture(saveUpdateBlogRequest.getPicture())
                    .shortDescription(saveUpdateBlogRequest.getShortDescription())
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now())
                    .build();

            BlogDetail blogDetail = BlogDetail.builder()
                    .description(saveUpdateBlogRequest.getDescription())
                    .blog(blog)
                    .build();
            blog.setBlogDetail(blogDetail);
            return blog;
    }

    public Blog updateBlogMapper(int idBlog, SaveUpdateBlogRequest saveUpdateBlogRequest) {
        if (saveUpdateBlogRequest == null) return null;
        Blog blog = Blog.builder()
                .id(idBlog)
                .title(saveUpdateBlogRequest.getTitle())
                .picture(saveUpdateBlogRequest.getPicture())
                .shortDescription(saveUpdateBlogRequest.getShortDescription())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        BlogDetail blogDetail = BlogDetail.builder()
                .id(idBlog)
                .description(saveUpdateBlogRequest.getDescription())
                .blog(blog)
                .build();

//        gán 2 chiều
        blog.setBlogDetail(blogDetail);
        return blog;
    }

}
