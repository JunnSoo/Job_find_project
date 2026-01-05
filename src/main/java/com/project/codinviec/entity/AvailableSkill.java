package com.project.codinviec.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "available_skills")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvailableSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "availableSkill", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailableSkillsJob> listAvailableSkillsJob = new ArrayList<>();

}
