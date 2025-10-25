package com.project.it_job.service;

import com.project.it_job.dto.BlogDTO;
import com.project.it_job.request.GetBlogRequest;
import com.project.it_job.request.SaveBlogRequest;
import com.project.it_job.request.UpdateBlogRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BlogService {
     List<BlogDTO> getAllBlog();
     Page<BlogDTO> getAllBlogPage(GetBlogRequest getBlogRequest);
     BlogDTO getBlogById(int id);
     BlogDTO saveBlog(SaveBlogRequest saveBlogRequest);
     BlogDTO updateBlogById(UpdateBlogRequest updateBlogRequest);
     BlogDTO deleteBlogById(int id);
}
