package com.project.it_job.repository.payment;

import com.project.it_job.entity.payment.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer>, JpaSpecificationExecutor<PaymentMethod> {
}
