package com.project.it_job.specification;

import com.project.it_job.entity.auth.Role;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class RoleSpecification {
    public static Specification<Role> searchByName(String keyword) {
        if (keyword == null || keyword.isEmpty()) return null; // neu trong tra null
        String pattern = "%" + keyword + "%"; //pattern
        return (root, query, cb) -> cb.like(root.get("roleName").as(String.class), pattern );
    }
}
