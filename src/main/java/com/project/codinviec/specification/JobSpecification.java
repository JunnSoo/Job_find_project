package com.project.codinviec.specification;

import com.project.codinviec.entity.Job;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
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
            try {
                int id = Integer.parseInt(keyword);
                return cb.equal(root.get("id"), id);
            } catch (NumberFormatException e) {
                return cb.like(cb.lower(root.get("jobPosition")), pattern);
            }
        };
    }

    public Specification<Job> searchCompanyName(String companyName) {
        if (companyName == null || companyName.isBlank()) {
            return null;
        }

        return (root, query, cb) -> {
            Join<Object, Object> companyJoin =
                    root.join("company", JoinType.INNER);

            return cb.like(
                    cb.lower(companyJoin.get("name")),
                    "%" + companyName.toLowerCase() + "%"
            );
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

    public Specification<Job> filterJob(
            String provinceName,
            List<String> industryNames,
            List<String> jobLevelNames,
            List<String> employmentTypeNames,
            Double salaryMin,
            Double salaryMax
    ) {
        return (root,  query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            // ===== Province =====
            if (provinceName != null && !provinceName.isBlank()) {
                Join<Object, Object> provinceJoin = root.join("province", JoinType.INNER);
                predicates.add(cb.equal(
                        cb.lower(provinceJoin.get("name")),
                        provinceName.toLowerCase()
                ));
            }

            // ===== Industry (IN) =====
            if (industryNames != null && !industryNames.isEmpty()) {
                Join<Object, Object> industryJoin = root.join("industry", JoinType.INNER);
                CriteriaBuilder.In<String> inClause = cb.in(industryJoin.get("name"));
                industryNames.forEach(name -> inClause.value(name));
                predicates.add(inClause);
            }

            // ===== Job Level (IN) =====
            if (jobLevelNames != null && !jobLevelNames.isEmpty()) {
                Join<Object, Object> jobLevelJoin = root.join("jobLevel", JoinType.INNER);
                CriteriaBuilder.In<String> inClause = cb.in(jobLevelJoin.get("name"));
                jobLevelNames.forEach(name -> inClause.value(name));
                predicates.add(inClause);
            }

            // ===== Employment Type (IN) =====
            if (employmentTypeNames != null && !employmentTypeNames.isEmpty()) {
                Join<Object, Object> employmentJoin = root.join("employmentType", JoinType.INNER);
                CriteriaBuilder.In<String> inClause = cb.in(employmentJoin.get("name"));
                employmentTypeNames.forEach(name -> inClause.value(name));
                predicates.add(inClause);
            }

            // ===== Salary =====
            if (salaryMin != null && salaryMax != null) {
                predicates.add(cb.between(root.get("salary"), salaryMin, salaryMax));
            }

            query.distinct(true); // tránh duplicate khi join nhiều bảng

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
