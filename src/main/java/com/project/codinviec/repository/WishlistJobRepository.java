package com.project.codinviec.repository;

import com.project.codinviec.entity.WishlistJob;
import com.project.codinviec.entity.key.WishlistJobKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistJobRepository extends JpaRepository<WishlistJob, WishlistJobKey> {
    List<WishlistJob> findByUser_Id(String userId);
}
