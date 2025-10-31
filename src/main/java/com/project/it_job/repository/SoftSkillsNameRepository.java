package com.project.it_job.repository;

import com.project.it_job.entity.SoftSkillsName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SoftSkillsNameRepository extends JpaRepository<SoftSkillsName,Integer>, JpaSpecificationExecutor<SoftSkillsName> {
}
