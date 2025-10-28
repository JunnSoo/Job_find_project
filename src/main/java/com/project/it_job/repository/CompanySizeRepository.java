package com.project.it_job.repository;

import com.project.it_job.entity.CompanySize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanySizeRepository extends JpaRepository<CompanySize, Integer>, JpaSpecificationExecutor<CompanySize> {
}
