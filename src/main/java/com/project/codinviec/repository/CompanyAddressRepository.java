package com.project.codinviec.repository;

import com.project.codinviec.entity.CompanyAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyAddressRepository extends JpaRepository<CompanyAddress, Integer>, JpaSpecificationExecutor<CompanyAddress> {
    List<CompanyAddress> findByCompany_Id(String companyId);

}
