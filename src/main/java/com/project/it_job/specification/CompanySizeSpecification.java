package com.project.it_job.specification;

import com.project.it_job.entity.Category;
import com.project.it_job.entity.CompanySize;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompanySizeSpecification {
        public Specification<CompanySize> searchByName(String keyword) {
            return (root, query, cb) -> {
                if (keyword == null || keyword.isEmpty()) return cb.conjunction(); // trả "true"
                String pattern = "%" + keyword + "%";

//                import jakarta mới được
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(cb.like(cb.toString(root.get("minEmployees")), pattern));
                predicates.add(cb.like(cb.toString(root.get("maxEmployees")), pattern));
                return cb.or(predicates.toArray(new Predicate[0]));
            };
    }
}
