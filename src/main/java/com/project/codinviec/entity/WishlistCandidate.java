package com.project.codinviec.entity;


import com.project.codinviec.entity.auth.User;
import com.project.codinviec.entity.key.WishlistCandidateKey;
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
    @JoinColumn(name = "candidate_id")
    private User userCandidate;


}
