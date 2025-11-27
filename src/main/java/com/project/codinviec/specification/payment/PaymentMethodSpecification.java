package com.project.codinviec.specification.payment;

import com.project.codinviec.entity.payment.PaymentMethod;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PaymentMethodSpecification {
    public Specification<PaymentMethod> searchByName(String keyword) {
        if(keyword==null || keyword.isEmpty()) return null;
        String pattern = "%" + keyword.toLowerCase() + "%";
        return (root, query, cb) -> cb.like(cb.lower(root.get("name")), pattern);
    }
}
