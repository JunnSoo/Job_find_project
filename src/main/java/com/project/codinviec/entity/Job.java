package com.project.codinviec.entity;
import com.project.codinviec.entity.auth.Company;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "job")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "job_position", nullable = false)
    private String jobPosition;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private  Company company;

    @Column(name = "detail_address", columnDefinition = "TEXT")
    private String detailAddress;

    @Column(name = "description_job", columnDefinition = "TEXT")
    private String descriptionJob;

    @Column(name = "requirement", columnDefinition = "TEXT")
    private String requirement;

    @Column(name = "benefits", columnDefinition = "TEXT")
    private String benefits;

    @Column(name = "province_id")
    private int provinceId;

    @Column(name = "industry_id")
    private int industryId;

    @Column(name = "job_level_id")
    private int jobLevelId;

    @Column(name = "degree_level_id")
    private int degreeLevelId;

    @Column(name = "employment_type_id")
    private int employmentTypeId;

    @Column(name = "experience_id")
    private int experienceId;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @OneToMany(mappedBy = "job",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WishlistJob> listWishlistJob = new ArrayList<>();

    @OneToMany(mappedBy = "idJob", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StatusSpecialJob> listStatusSpecialJob = new ArrayList<>();;
}
