package com.project.it_job.specification.payment;

import com.project.it_job.entity.payment.PaymentMethod;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PaymentMethodSpecification {
    public Specification<PaymentMethod> searchByName(String keyword) {
        if(keyword==null || keyword.isEmpty()) return null;
        String pattern = "%" + keyword + "%"; //pattern
        return (root, query, cb) -> cb.like(root.get("name").as(String.class), pattern );
    }
}
