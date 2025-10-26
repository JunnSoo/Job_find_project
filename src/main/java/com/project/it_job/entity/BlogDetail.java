package com.project.it_job.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "blog_detail")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Blog blog;

}
