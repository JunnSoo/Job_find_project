package com.project.it_job.mapper;

import com.project.it_job.dto.BlogDTO;
import com.project.it_job.dto.BlogDetailDTO;
import com.project.it_job.entity.Blog;
import org.springframework.stereotype.Component;

@Component
public class BlogDetailMapper {
    public BlogDetailDTO blogToBlogDetailDTO(Blog blog){
        if (blog == null || blog.getBlogDetail() == null) return  null;
        BlogDetailDTO blogDetailDTO = new BlogDetailDTO();
        blogDetailDTO.setId(blog.getId());
        blogDetailDTO.setDescription(blog.getBlogDetail().getDescription());
        return blogDetailDTO;
    }
}
