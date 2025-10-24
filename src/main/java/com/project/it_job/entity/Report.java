package com.project.it_job.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table(name= "report")
@Getter
@Setter
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;
    @Column(name = "hinh_anh", columnDefinition = "TEXT")
    private String hinhAnh;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private ReportStatus statusId;
    @Column(name = "created_report", length = 36, nullable = false)
    private String createdReport;
    @Column(name = "reported_user", length = 36)
    private String reportedUser;
    @Column(name = "reported_job")
    private Integer reportedJob;


}
