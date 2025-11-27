package com.project.codinviec.repository;


import com.project.codinviec.entity.WishlistCandidate;
import com.project.codinviec.entity.key.WishlistCandidateKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistCandidateRepository extends JpaRepository<WishlistCandidate, WishlistCandidateKey> {
    List<WishlistCandidate> findByUserHr_Id(String idHr);
}
