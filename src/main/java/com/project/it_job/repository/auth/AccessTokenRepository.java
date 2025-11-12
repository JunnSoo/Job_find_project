package com.project.it_job.repository.auth;

import com.project.it_job.entity.auth.AccessToken;
import com.project.it_job.entity.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken, Integer> {
    Optional<AccessToken> findByToken(String token);
    List<AccessToken> findByUser_Id(String user_id);
    List<AccessToken> findByUser_Email(String email);
}
