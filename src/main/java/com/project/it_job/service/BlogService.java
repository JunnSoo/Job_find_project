package com.project.it_job.service;

import com.project.it_job.dto.BlogDTO;
import com.project.it_job.dto.BlogDetailDTO;
import com.project.it_job.request.GetBlogRequest;
import com.project.it_job.request.SaveUpdateBlogRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BlogService {
     List<BlogDTO> getAllBlog();
     Page<BlogDTO> getAllBlogPage(GetBlogRequest getBlogRequest);
     BlogDTO getBlogById(int id);
     BlogDTO saveBlog(SaveUpdateBlogRequest saveUpdateBlogRequest );
     BlogDTO updateBlogById(int idBlog,SaveUpdateBlogRequest saveUpdateBlogRequest);
     BlogDTO deleteBlogById(int id);
     BlogDetailDTO  getBlogDetailById(int id);
}
