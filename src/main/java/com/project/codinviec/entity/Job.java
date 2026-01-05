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

    @Column(name = "is_agreed_salary")
    private Boolean isAgreedSalary;

    @Column(name = "salary")
    private double salary;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id")
    private Province province;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "industry_id")
    private Industry industry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_level_id")
    private JobLevel jobLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "degree_level_id")
    private DegreeLevel degreeLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employment_type_id")
    private EmploymentType employmentType;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "experience_id")
    private Experience experience;

    @OneToMany(mappedBy = "job",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WishlistJob> listWishlistJob = new ArrayList<>();

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StatusSpecialJob> listStatusSpecialJob = new ArrayList<>();

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailableSkillsJob> listAvailableSkillsJob = new ArrayList<>();

}
