package com.project.it_job.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "province")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 255)
    private String name;

    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ward> wards;
}
