package com.project.it_job.service;

import com.project.it_job.dto.BlogDTO;
import com.project.it_job.dto.BlogDetailDTO;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.SaveUpdateBlogRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BlogService {
     List<BlogDTO> getAllBlog();
     Page<BlogDTO> getAllBlogPage(PageRequestCustom pageRequestCustom);
     BlogDTO getBlogById(Integer id);
     BlogDTO saveBlog(SaveUpdateBlogRequest saveUpdateBlogRequest );
     BlogDTO updateBlogById(Integer idBlog,SaveUpdateBlogRequest saveUpdateBlogRequest);
     BlogDTO deleteBlogById(Integer id);
     BlogDetailDTO getBlogDetailById(Integer id);
}
