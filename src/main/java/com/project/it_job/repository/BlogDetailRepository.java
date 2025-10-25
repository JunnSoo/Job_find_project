package com.project.it_job.repository;

import com.project.it_job.entity.BlogDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogDetailRepository extends JpaRepository<BlogDetail,Integer> {
}
