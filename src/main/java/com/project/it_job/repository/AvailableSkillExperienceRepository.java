package com.project.it_job.repository;

import com.project.it_job.entity.AvailableSkillExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailableSkillExperienceRepository extends JpaRepository<AvailableSkillExperience, Integer> {

    List<AvailableSkillExperience> findByUser_Id(String userId);

    boolean existsByUser_IdAndAvailableSkill_IdAndExperience_Id(String userId, Integer availableSkillId, Integer experienceId);
}
