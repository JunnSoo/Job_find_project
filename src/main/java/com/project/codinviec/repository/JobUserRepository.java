package com.project.codinviec.repository;

import com.project.codinviec.entity.JobUser;
import com.project.codinviec.entity.auth.Company;
import com.project.codinviec.entity.key.JobUserKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobUserRepository extends JpaRepository<JobUser, JobUserKey> {
    List<JobUser> findByJob_Company(Company company);
}
