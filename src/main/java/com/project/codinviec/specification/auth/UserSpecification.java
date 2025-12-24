package com.project.codinviec.specification.auth;

import com.project.codinviec.entity.auth.User;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserSpecification {
    public Specification<User> searchByName(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) return null;

        String pattern = "%" + keyword.toLowerCase().trim() + "%";

        return (root, query, cb) -> {
            Expression<String> first = cb.lower(root.get("firstName"));
            Expression<String> last = cb.lower(root.get("lastName"));
            Expression<String> fullName = cb.lower(
                    cb.concat(
                            cb.concat(root.get("firstName"), " "),
                            root.get("lastName")
                    )
            );
            return cb.or(
                    cb.like(first, pattern),
                    cb.like(last, pattern),
                    cb.like(fullName, pattern)
            );
        };
    }

    public Specification<User> hasRoleId(String roleId) {
        return (root, query, cb) -> {
            if (roleId == null || roleId.trim().isEmpty()) {
                return cb.conjunction();
            }
            Join<Object, Object> roleJoin = root.join("role");
            return cb.equal(roleJoin.get("id"), roleId);
        };
    }

    public Specification<User> isBlocked(Boolean isBlock) {
        return (root, query, cb) -> {
            if (isBlock == null) {
                return cb.conjunction();
            }
            return Boolean.TRUE.equals(isBlock)
                    ? cb.isTrue(root.get("isBlock"))
                    : cb.isFalse(root.get("isBlock"));
        };
    }
}
