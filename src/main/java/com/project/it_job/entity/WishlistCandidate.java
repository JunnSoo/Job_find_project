package com.project.it_job.entity;

import com.project.it_job.KeyEntity.WishlistCandidateKey;
import com.project.it_job.entity.auth.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "wishlist_candidate")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class WishlistCandidate {

    @EmbeddedId
    private WishlistCandidateKey wishlistCandidateKey;

    @MapsId("hrId")
    @ManyToOne
    @JoinColumn(name = "hr_id")
    private User userHr;

    @MapsId("candidateId")
    @ManyToOne
    @JoinColumn(name = "ungvien_id")
    private User userCandidate;


}
