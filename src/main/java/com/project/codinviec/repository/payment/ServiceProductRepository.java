package com.project.codinviec.repository.payment;

import com.project.codinviec.entity.payment.ServiceProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceProductRepository extends JpaRepository<ServiceProduct, Integer>, JpaSpecificationExecutor<ServiceProduct> {
}
