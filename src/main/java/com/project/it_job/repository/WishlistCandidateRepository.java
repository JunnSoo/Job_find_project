package com.project.it_job.repository;

import com.project.it_job.keyentity.WishlistCandidateKey;
import com.project.it_job.entity.WishlistCandidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistCandidateRepository extends JpaRepository<WishlistCandidate, WishlistCandidateKey> {
    List<WishlistCandidate> findByUserHr_Id(String idHr);
}
