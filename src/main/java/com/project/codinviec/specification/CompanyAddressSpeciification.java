package com.project.codinviec.specification;

import com.project.codinviec.entity.CompanyAddress;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CompanyAddressSpeciification {
    public Specification<CompanyAddress> searchById(String id) {
        if (id == null || id.isEmpty()) return null;
        String pattern = "%" + id.toLowerCase() + "%";
        return (root, query, cb) -> cb.like(cb.lower(root.get("id")), pattern);
    }

    public Specification<CompanyAddress> filterByProvinceId(String provinceId) {
        if (provinceId == null || provinceId.isEmpty()) return null;
        String pattern = "%" + provinceId.toLowerCase() + "%";
        return (root, query, cb) -> cb.like(cb.lower(root.get("province").get("id")), pattern);
    }
    public Specification<CompanyAddress> filterByWardId(String wardId) {
        if (wardId == null || wardId.isEmpty()) return null;
        String pattern = "%" + wardId.toLowerCase() + "%";
        return (root, query, cb) -> cb.like(cb.lower(root.get("ward").get("id")), pattern);
    }

}
