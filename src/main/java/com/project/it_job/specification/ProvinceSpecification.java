package com.project.it_job.specification;

import com.project.it_job.entity.Province;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProvinceSpecification {
    public Specification<Province> searchByName(String keyword) {
        if (keyword == null || keyword.isEmpty()) return null;
        String pattern = "%" + keyword.toLowerCase() + "%";
        return (root, query, cb) -> cb.like(cb.lower(root.get("name")), pattern);
    }
}

