package com.project.it_job.repository.payment;

import com.project.it_job.entity.payment.Payment;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer>, JpaSpecificationExecutor<Payment> {
}
