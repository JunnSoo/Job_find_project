package com.project.codinviec.repository;

import com.project.codinviec.entity.StatusSpecialJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusSpecialJobRepository extends JpaRepository<StatusSpecialJob,Integer> {
    List<StatusSpecialJob> findByIdJob_Id(Integer id);
    List<StatusSpecialJob> findByIdStatusSpecial_Id(Integer id);
}
