package com.project.codinviec.repository;

import com.project.codinviec.entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WardRepository extends JpaRepository<Ward, Integer>, JpaSpecificationExecutor<Ward> {
}
