package com.project.codinviec.specification;

import com.project.codinviec.entity.Job;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class JobSpecification {
    public Specification<Job> searchByName(String keyword) {
        if (keyword == null || keyword.isEmpty()) return null;

        String pattern = "%" + keyword.toLowerCase() + "%";

        return (root, query, cb) -> {
            // Nếu keyword là số thì tìm theo ID
            try {
                int id = Integer.parseInt(keyword);
                return cb.equal(root.get("id"), id);
            } catch (NumberFormatException e) {
                // Nếu không phải số thì tìm theo jobPosition (chứa keyword)
                return cb.like(cb.lower(root.get("jobPosition")), pattern);
            }
        };
    }
}
