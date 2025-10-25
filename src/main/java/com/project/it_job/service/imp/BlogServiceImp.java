package com.project.it_job.service.imp;

import com.project.it_job.dto.BlogDTO;
import com.project.it_job.entity.Blog;
import com.project.it_job.entity.BlogDetail;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
        Blog blog = blogRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundIdExceptionHandler("Không tìm thấy ID user");
        });
        return  blogMapper.blogToDTO(blog);
    }

    @Override
    @Transactional
    public BlogDTO saveBlog(SaveBlogRequest saveBlogRequest) {
        Blog blog = blogMapper.saveBlogToBlog(saveBlogRequest);

        BlogDetail blogDetail =new BlogDetail();
        blogDetail.setDescription(saveBlogRequest.getDescription());
        blogDetail.setBlog(blog);

        blog.setBlogDetail(blogDetail);
        return  blogMapper.blogToDTO(blogRepository.save(blog));
    }

    @Override
    @Transactional
    public BlogDTO updateBlogById(UpdateBlogRequest updateBlogRequest) {
        Blog blog = blogRepository.findById(updateBlogRequest.getId()).orElseThrow(() -> {
            System.out.println("BlogServiceImp : Không tìm thấy ID user");
            throw new NotFoundIdExceptionHandler();
        });
        Blog mappedBlog = blogMapper.updateBlogToBlog(updateBlogRequest);
        mappedBlog.setCreatedDate(blog.getCreatedDate());
        mappedBlog.setUpdatedDate(LocalDateTime.now());

        return  blogMapper.blogToDTO(blogRepository.save(mappedBlog));
    }

    @Override
    public BlogDTO deleteBlogById(int id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> {
            System.out.println("BlogServiceImp : Không tìm thấy ID user");
            throw new NotFoundIdExceptionHandler();
        });
        blogRepository.delete(blog);
        return blogMapper.blogToDTO(blog);
    }


}
