package com.project.it_job.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "report_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    @Column(length = 50 , nullable = false)
    private String name;

    @OneToMany(mappedBy = "statusId", cascade = CascadeType.ALL)
    private List<Report> reports;
}
