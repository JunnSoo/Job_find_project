package com.project.it_job.repository.auth;

import com.project.it_job.entity.auth.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,String>, JpaSpecificationExecutor<Company> {
}
