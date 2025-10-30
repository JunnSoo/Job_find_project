package com.project.it_job.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "job")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "job_position", length = 255, nullable = false)
    private String jobPosition;

    @Column(name = "company_id", length = 36, nullable = false)
    private String companyId;

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
}
