package com.project.it_job.repository.auth;

import com.project.it_job.entity.auth.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken, Integer> {
    Optional<AccessToken> findByToken(String token);

    List<AccessToken> findByUser_Id(String user_id);

    boolean existsByUser_IdAndIsRevokedFalse(String userId);

    @Modifying
    @Query("UPDATE access_token a SET a.isRevoked = true WHERE a.user.id = :userId")
    void revokeAllAccessTokens(String userId);
}
