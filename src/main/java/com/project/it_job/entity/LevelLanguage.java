package com.project.it_job.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "level_language")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LevelLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
}
