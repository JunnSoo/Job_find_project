package com.project.codinviec.entity.auth;

import com.project.codinviec.entity.CompanyAddress;
import com.project.codinviec.entity.CompanySize;
import com.project.codinviec.entity.Job;
import com.project.codinviec.entity.Review;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "company")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {
    @Id
    private String id;
    private String name;
    private String description;
    private String website;
    private String logo;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @ManyToOne
    @JoinColumn(name = "company_size_id")
    private CompanySize companySize;

    @OneToMany(mappedBy = "company")
    private List<Review> listReview = new ArrayList<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Job> jobs = new ArrayList<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    List<CompanyAddress> companyAddresses = new ArrayList<>();
}
