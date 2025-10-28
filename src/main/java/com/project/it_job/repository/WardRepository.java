package com.project.it_job.repository;

import com.project.it_job.dto.WardDTO;
import com.project.it_job.entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WardRepository extends JpaRepository<Ward, Integer> {
}
