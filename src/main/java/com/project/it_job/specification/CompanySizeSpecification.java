package com.project.it_job.specification;

import com.project.it_job.entity.CompanySize;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompanySizeSpecification {
    public Specification<CompanySize> searchByName(String keyword) {
        if (keyword == null || keyword.isEmpty())
            return null;
        String pattern = "%" + keyword.toLowerCase() + "%";
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.like(cb.lower(cb.toString(root.get("minEmployees"))), pattern));
            predicates.add(cb.like(cb.lower(cb.toString(root.get("maxEmployees"))), pattern));
            return cb.or(predicates.toArray(new Predicate[0]));
        };
    }
}
