package com.project.codinviec.repository;

import com.project.codinviec.entity.AvailableSkillsJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvailableSkillsJobRepository extends JpaRepository<AvailableSkillsJob, Integer> {
    List<AvailableSkillsJob> findByJob_Id(int jobId);
}
