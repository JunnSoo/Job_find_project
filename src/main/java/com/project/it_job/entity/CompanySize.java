package com.project.it_job.entity;

import com.project.it_job.entity.auth.Company;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "company_size")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanySize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int minEmployees;
    private int maxEmployees;

    @OneToMany(mappedBy = "companySize")
    private List<Company> companies;
}
