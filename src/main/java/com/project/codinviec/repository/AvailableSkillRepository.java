package com.project.codinviec.repository;

import com.project.codinviec.entity.AvailableSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableSkillRepository extends JpaRepository<AvailableSkill, Integer> {
}
