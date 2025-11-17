package com.project.it_job.specification.auth;

import com.project.it_job.entity.auth.Role;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class RoleSpecification {
    public Specification<Role> searchByName(String keyword) {
        if (keyword == null || keyword.isEmpty()) return null;
        String pattern = "%" + keyword.toLowerCase() + "%";
        return (root, query, cb) -> cb.like(cb.lower(root.get("roleName")), pattern);
    }
}
