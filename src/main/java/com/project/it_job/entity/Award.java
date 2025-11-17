package com.project.it_job.entity;

import com.project.it_job.entity.auth.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "award")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Award {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "award_name")
    private String awardName;

    private String organization;

    private LocalDateTime date;

    @Column(columnDefinition = "TEXT")
    private String description;
}
