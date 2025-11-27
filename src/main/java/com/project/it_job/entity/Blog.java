package com.project.it_job.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "blog")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String picture;
    private String shortDescription;
    private String description;
    private boolean isHighLight;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String updatedPerson;
}
