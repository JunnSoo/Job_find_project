package com.project.it_job.repository;

import com.project.it_job.entity.AvailableSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableSkillRepository extends JpaRepository<AvailableSkill, Integer> {
}
