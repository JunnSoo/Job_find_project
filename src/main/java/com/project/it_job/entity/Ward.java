package com.project.it_job.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ward")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_province")
    private Province province;
}
