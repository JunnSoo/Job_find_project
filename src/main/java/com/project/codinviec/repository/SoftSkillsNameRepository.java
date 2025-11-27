package com.project.codinviec.repository;

import com.project.codinviec.entity.SoftSkillsName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SoftSkillsNameRepository extends JpaRepository<SoftSkillsName,Integer>, JpaSpecificationExecutor<SoftSkillsName> {
}
