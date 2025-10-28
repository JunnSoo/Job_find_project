package com.project.it_job.specification;

import com.project.it_job.entity.Blog;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class BlogSpecification {
    public Specification<Blog> searchByName(String keyword) {
        if (keyword == null || keyword.isEmpty()) return null; // neu trong tra null
        String pattern = "%" + keyword + "%"; //pattern
        return (root, query, cb) -> cb.like(root.get("title").as(String.class), pattern );
    }
}
