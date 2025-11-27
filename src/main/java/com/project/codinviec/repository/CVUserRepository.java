package com.project.codinviec.repository;

import com.project.codinviec.entity.CVUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CVUserRepository extends JpaRepository<CVUser,Integer>, JpaSpecificationExecutor<CVUser> {
    Optional<CVUser> findFirstByCandidate_IdOrderByVersionDesc(String candidateId);
}
