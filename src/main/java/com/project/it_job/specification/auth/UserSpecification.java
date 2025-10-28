package com.project.it_job.specification.auth;

import com.project.it_job.entity.auth.User;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserSpecification {
    public Specification<User> searchByName(String keyword) {

        return (root, query, cb) -> {
            if (keyword == null || keyword.isEmpty()) return null; // neu trong tra null
            String pattern = "%" + keyword + "%"; //pattern
            
            //                import jakarta mới được
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.like(cb.toString(root.get("firstName")), pattern));
            predicates.add(cb.like(cb.toString(root.get("lastName")), pattern));
            return cb.or(predicates.toArray(new Predicate[0]));
        };
    }
}
