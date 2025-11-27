package com.project.codinviec.repository.auth;

import com.project.codinviec.entity.auth.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,String>, JpaSpecificationExecutor<Company> {
}
