package com.project.it_job.repository.auth;

import com.project.it_job.entity.auth.RefreshToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByToken(String token);
    List<RefreshToken> findByUser_Id(String userId);
    boolean existsByUser_IdAndIsRevokedFalse(String userId);

    @Modifying
    @Query("UPDATE refresh_token a SET a.isRevoked = true WHERE a.user.id = :userId")
    void revokeAllRefreshTokens(String userId);
}
