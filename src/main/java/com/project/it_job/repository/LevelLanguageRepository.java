package com.project.it_job.repository;

import com.project.it_job.entity.LevelLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelLanguageRepository extends JpaRepository<LevelLanguage, Integer> {
}
