package com.project.codinviec.repository;

import com.project.codinviec.entity.LevelLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelLanguageRepository extends JpaRepository<LevelLanguage, Integer> {
}
