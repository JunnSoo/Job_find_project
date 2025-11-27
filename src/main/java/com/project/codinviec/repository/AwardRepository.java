package com.project.codinviec.repository;

import com.project.codinviec.entity.Award;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AwardRepository extends JpaRepository<Award, Integer> {
    List<Award> findByUser_Id(String userId);
}
