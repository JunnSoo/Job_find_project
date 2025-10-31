package com.project.it_job.repository;

import com.project.it_job.entity.WishlistJob;
import com.project.it_job.keyentity.WishlistJobKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistJobRepository extends JpaRepository<WishlistJob, WishlistJobKey> {
    List<WishlistJob> findByUser_Id(String userId);
}
