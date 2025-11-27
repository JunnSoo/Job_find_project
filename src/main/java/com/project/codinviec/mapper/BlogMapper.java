package com.project.codinviec.mapper;

import com.project.codinviec.dto.BlogDTO;
import com.project.codinviec.dto.BlogDetailDTO;
import com.project.codinviec.entity.Blog;
import com.project.codinviec.request.SaveUpdateBlogRequest;
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
                .isHighLight(blog.isHighLight())
                .createdDate(blog.getCreatedDate())
                .updatedDate(blog.getUpdatedDate())
                .build();
    }

    public BlogDetailDTO blogToBlogDetailDTO(Blog blog) {
        return BlogDetailDTO.builder()
                .id(blog.getId())
                .description(blog.getDescription())
                .build();
    }

    public Blog saveBlogMapper(SaveUpdateBlogRequest  saveUpdateBlogRequest) {
        if (saveUpdateBlogRequest == null) return null;
        return Blog.builder()
                    .title(saveUpdateBlogRequest.getTitle())
                    .picture(saveUpdateBlogRequest.getPicture())
                    .shortDescription(saveUpdateBlogRequest.getShortDescription())
                    .description(saveUpdateBlogRequest.getDescription())
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now())
                    .build();

    }

    public Blog updateBlogMapper(int idBlog, SaveUpdateBlogRequest saveUpdateBlogRequest) {
        if (saveUpdateBlogRequest == null) return null;
        return Blog.builder()
                .id(idBlog)
                .title(saveUpdateBlogRequest.getTitle())
                .picture(saveUpdateBlogRequest.getPicture())
                .shortDescription(saveUpdateBlogRequest.getShortDescription())
                .description(saveUpdateBlogRequest.getDescription())
                .isHighLight(saveUpdateBlogRequest.getIsHighLight())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();


    }

}
