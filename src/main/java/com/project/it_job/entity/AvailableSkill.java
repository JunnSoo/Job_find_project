package com.project.it_job.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "available_skill")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AvailableSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
}
