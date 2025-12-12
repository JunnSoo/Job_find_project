package com.project.codinviec.repository;

import com.project.codinviec.entity.StatusSpecial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface StatusSpecialRepository extends JpaRepository<StatusSpecial, Integer>, JpaSpecificationExecutor<StatusSpecial> {
}

