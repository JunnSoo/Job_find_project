package com.project.it_job.specification.payment;

import com.project.it_job.entity.payment.Payment;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PaymentSpecification {
    public Specification<Payment> searchByTitle(String keyword) {
        if(keyword==null || keyword.isEmpty()) return null;
        String pattern = "%" + keyword.toLowerCase() + "%";
        return (root, query, cb) -> cb.like(cb.lower(root.get("title")), pattern);
    }
}
