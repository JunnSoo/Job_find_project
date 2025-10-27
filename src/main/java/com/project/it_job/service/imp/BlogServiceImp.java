package com.project.it_job.service.imp;

import com.project.it_job.dto.BlogDTO;
import com.project.it_job.dto.BlogDetailDTO;
import com.project.it_job.entity.Blog;
import com.project.it_job.exception.ConflictException;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.exception.ParamExceptionHandler;
import com.project.it_job.mapper.BlogMapper;
import com.project.it_job.repository.BlogRepository;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.SaveUpdateBlogRequest;
import com.project.it_job.service.BlogService;
import com.project.it_job.specification.BlogSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogServiceImp implements BlogService {

    private final BlogRepository blogRepository;

    private final BlogMapper blogMapper;



    @Override
    public List<BlogDTO> getAllBlog() {
        return blogRepository.findAll().stream().map(blog -> blogMapper.blogToDTO(blog)).toList();
    }



    @Override
    public Page<BlogDTO> getAllBlogPage(PageRequestCustom pageRequestCustom) {
        //       Trường hợp keyword = null
        if (pageRequestCustom.getKeyword() == null) {
            pageRequestCustom.setKeyword("");
        }else{
//            set pageSize mặc định khi có keyword và pageSize không được truyền
            if (pageRequestCustom.getPageSize() == 0) {
                pageRequestCustom.setPageSize(5);            }
        }

//        truyền pageSize không hợp lệ ( > 0 mới tính)
        if (pageRequestCustom.getPageSize() <= 0)
            throw new ParamExceptionHandler("Truyền pageSize không hợp lệ!");

        Pageable pageable = PageRequest.of(pageRequestCustom.getPageNumber(), pageRequestCustom.getPageSize());
        Specification<Blog> spec = Specification.allOf(BlogSpecification.searchByName(pageRequestCustom.getKeyword()));
        return blogRepository.findAll(spec, pageable).map( blog -> blogMapper.blogToDTO(blog));
    }


    @Override
    public BlogDTO getBlogById(int id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy user ID"));
        return  blogMapper.blogToDTO(blog);
    }

    @Override
    @Transactional
    public BlogDTO saveBlog(SaveUpdateBlogRequest saveUpdateBlogRequest) {
       try{
           Blog blog = blogMapper.saveBlogMapper(saveUpdateBlogRequest);
           return  blogMapper.blogToDTO(blogRepository.save(blog));
       }catch (Exception e){
            throw new ConflictException("Lỗi thêm blog!");
       }
    }

    @Override
    @Transactional
    public BlogDTO updateBlogById(int idBlog, SaveUpdateBlogRequest saveUpdateBlogRequest) {
        Blog blog = blogRepository.findById(idBlog).orElseThrow(()
                -> new NotFoundIdExceptionHandler("Không tìm thấy user ID"));
        Blog mappedBlog = blogMapper.updateBlogMapper(idBlog,saveUpdateBlogRequest);
        mappedBlog.setCreatedDate(blog.getCreatedDate());
        return  blogMapper.blogToDTO(blogRepository.save(mappedBlog));
    }

    @Override
    public BlogDTO deleteBlogById(int id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy user ID"));
        blogRepository.delete(blog);
        return blogMapper.blogToDTO(blog);
    }

    @Override
    public BlogDetailDTO getBlogDetailById(int id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy user ID"));
        return blogMapper.blogToBlogDetailDTO(blog);
    }
}