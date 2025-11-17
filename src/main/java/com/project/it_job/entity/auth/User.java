package com.project.it_job.entity.auth;

import com.project.it_job.entity.Review;
import com.project.it_job.entity.SoftSkillsName;
import com.project.it_job.entity.WishlistCandidate;
import com.project.it_job.entity.WishlistJob;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String avatar;
    private String phone;
    private String gender;
    private String education;
    private String address;
    @Column(name = "website_link")
    private String websiteLink;
    private LocalDateTime birthDate;
    private boolean isFindJob;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String updatedPerson;
    private String groupSoftSkill;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Review> listReview;

    @OneToMany(mappedBy = "user")
    private List<SoftSkillsName> listSoftSkillsName;

    // ds wishlist của bản thân
    @OneToMany(mappedBy = "userHr", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WishlistCandidate> wishlistCandidates = new ArrayList<>();;

    // bị wistlist
    @OneToMany(mappedBy = "userCandidate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WishlistCandidate> wishedByHRs = new ArrayList<>();;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WishlistJob> listWishlistJob = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccessToken> listAccessToken = new ArrayList<>();
}
