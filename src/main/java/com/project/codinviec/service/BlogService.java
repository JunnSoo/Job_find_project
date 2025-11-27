package com.project.codinviec.service;

import com.project.codinviec.dto.BlogDTO;
import com.project.codinviec.dto.BlogDetailDTO;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.SaveUpdateBlogRequest;
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
