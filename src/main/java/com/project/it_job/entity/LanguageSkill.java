package com.project.it_job.entity;

import com.project.it_job.entity.auth.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "language_skill")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LanguageSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne
    @JoinColumn(name = "level_language_id")
    private LevelLanguage levelLanguage;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
