package com.project.it_job.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "industry")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Industry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 255, nullable = false)
    private String name;
}
