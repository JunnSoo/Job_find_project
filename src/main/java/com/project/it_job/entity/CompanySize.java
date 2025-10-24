package com.project.it_job.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="company_size")
@Getter
@Setter
public class CompanySize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "min_employees", nullable = false)
    private int minEmployees;
    @Column(name = "max_employees", nullable = false)
    private int maxEmployees;
}
