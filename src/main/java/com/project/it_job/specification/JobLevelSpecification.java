package com.project.it_job.specification;

import com.project.it_job.entity.JobLevel;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class JobLevelSpecification {
    public Specification<JobLevel> searchByName(String keyword) {
        if (keyword == null || keyword.isEmpty()) return null;
        String pattern = "%" + keyword.toLowerCase() + "%";
        return (root, query, cb) -> cb.like(cb.lower(root.get("name")), pattern);
    }
}

