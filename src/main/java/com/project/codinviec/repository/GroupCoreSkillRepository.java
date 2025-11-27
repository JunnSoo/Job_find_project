package com.project.codinviec.repository;

import com.project.codinviec.entity.GroupCoreSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupCoreSkillRepository extends JpaRepository<GroupCoreSkill, Integer> {
}
