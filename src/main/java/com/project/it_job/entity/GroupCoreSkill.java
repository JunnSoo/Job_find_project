package com.project.it_job.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "group_core_skill")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupCoreSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
}
