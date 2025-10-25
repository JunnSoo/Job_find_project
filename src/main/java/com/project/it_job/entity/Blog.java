package com.project.it_job.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "blog")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String picture;
    private String shortDescription;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String updatedPerson;

    @OneToOne(mappedBy = "blog", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private BlogDetail blogDetail;

}
