package com.project.codinviec.repository.payment;

import com.project.codinviec.entity.payment.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer>, JpaSpecificationExecutor<PaymentMethod> {
    Optional<PaymentMethod> findByNameIgnoreCase(String name);
}
