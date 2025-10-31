package com.project.it_job.repository;

import com.project.it_job.KeyEntity.WishlistCandidateKey;
import com.project.it_job.entity.WishlistCandidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistCandidateRepository extends JpaRepository<WishlistCandidate, WishlistCandidateKey> {
    List<WishlistCandidate> findByUserHr_Id(String idHr);
}
