package com.project.codinviec.specification;

import com.project.codinviec.entity.Job;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    public Specification<Job> searchByNameAndProvinceId(String keyword, int provinceId) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();



            // Nếu có provinceId thêm điều kiện
            if (provinceId != 0) {
                predicates.add(cb.equal(root.get("provinceId"), provinceId));
            }

            // Nếu keyword là số tìm theo jobPosition id
            try {
                int id = Integer.parseInt(keyword);
                predicates.add(cb.equal(root.get("jobPosition"),id));

            } catch (NumberFormatException e) {
                // Nếu keyword là chữ LIKE theo jobPosition.name
                String pattern = "%" + keyword.toLowerCase() + "%";
                predicates.add(
                        cb.like(cb.lower(root.get("jobPosition")), pattern)
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
