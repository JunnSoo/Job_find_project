package com.project.codinviec.specification.auth;

import com.project.codinviec.entity.CompanyAddress;
import com.project.codinviec.entity.CompanySize;
import com.project.codinviec.entity.auth.Company;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CompanySpecification {

    /**
     * Tìm theo tên công ty (LIKE, ignore case)
     */
    public Specification<Company> searchByName(String keyword) {
        return (root, query, cb) -> {
            if (keyword == null || keyword.trim().isEmpty()) {
                return cb.conjunction();
            }
            return cb.like(
                    cb.lower(root.get("name")),
                    "%" + keyword.trim().toLowerCase() + "%"
            );
        };
    }

    /**
     * Filter theo số nhân viên tối thiểu
     * LOGIC ĐÚNG: companySize.maxEmployees >= min
     */
    public Specification<Company> minEmployees(Integer min) {
        return (root, query, cb) -> {
            if (min == null) {
                return cb.conjunction();
            }

            Join<Company, CompanySize> sizeJoin =
                    root.join("companySize", JoinType.LEFT); // ⚠ LEFT JOIN

            return cb.greaterThanOrEqualTo(
                    sizeJoin.get("maxEmployees"),
                    min
            );
        };
    }

    /**
     * Filter theo số nhân viên tối đa
     * LOGIC ĐÚNG: companySize.minEmployees <= max
     */
    public Specification<Company> maxEmployees(Integer max) {
        return (root, query, cb) -> {
            if (max == null) {
                return cb.conjunction();
            }

            Join<Company, CompanySize> sizeJoin =
                    root.join("companySize", JoinType.LEFT); // ⚠ LEFT JOIN

            return cb.lessThanOrEqualTo(
                    sizeJoin.get("minEmployees"),
                    max
            );
        };
    }

    /**
     * Lọc theo tỉnh (province)
     */
    public Specification<Company> hasProvince(String provinceName) {
        return (root, query, cb) -> {
            if (provinceName == null || provinceName.trim().isEmpty()) {
                return cb.conjunction();
            }

            query.distinct(true); // tránh duplicate do OneToMany

            Join<Company, CompanyAddress> addressJoin =
                    root.join("companyAddresses", JoinType.LEFT); // ⚠ LEFT JOIN

            return cb.like(
                    cb.lower(addressJoin.get("province").get("name")),
                    "%" + provinceName.trim().toLowerCase() + "%"
            );
        };
    }

    /**
     * Chỉ lấy trụ sở chính
     */
    public Specification<Company> isHeadOffice(Boolean headOffice) {
        return (root, query, cb) -> {
            if (headOffice == null) {
                return cb.conjunction();
            }

            Join<Company, CompanyAddress> addressJoin =
                    root.join("companyAddresses", JoinType.LEFT); // ⚠ LEFT JOIN

            return cb.equal(addressJoin.get("headOffice"), headOffice);
        };
    }
}
