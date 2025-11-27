package com.project.codinviec.repository;

import com.project.codinviec.entity.LanguageSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageSkillRepository extends JpaRepository<LanguageSkill, Integer> {
    List<LanguageSkill> findByUser_Id(String userId);
    boolean existsByUser_IdAndLanguage_Id(String userId, Integer languageId);
}
