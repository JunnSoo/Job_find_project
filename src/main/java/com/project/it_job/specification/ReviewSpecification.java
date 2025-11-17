package com.project.it_job.specification;

import com.project.it_job.entity.Review;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewSpecification {
    public Specification<Review> searchByName(String keyword) {
        if (keyword == null || keyword.isEmpty()) return null;
        String pattern = "%" + keyword.toLowerCase() + "%";
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.like(cb.lower(cb.toString(root.get("title"))), pattern));
            predicates.add(cb.like(cb.lower(cb.toString(root.get("description"))), pattern));
            return cb.or(predicates.toArray(new Predicate[0]));
        };
    }
}
