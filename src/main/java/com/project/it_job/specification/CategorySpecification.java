package com.project.it_job.specification;

import com.project.it_job.entity.Category;
import org.springframework.data.jpa.domain.Specification;

public class CategorySpecification {
        public static Specification<Category> searchByName(String keyword) {
            if (keyword == null || keyword.isEmpty()) return null; // neu trong tra null
            String pattern = "%" + keyword + "%"; //pattern
            return (root, query, cb) -> cb.like(root.get("name").as(String.class), pattern );
        }
        public static Specification<Category> parentIsNull() {
            return (root, query, cb) -> cb.isNull(root.get("parent"));
        }
}
