package com.project.codinviec.entity;

import com.project.codinviec.entity.auth.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "available_skill_experience")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvailableSkillExperience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // nhóm kỹ năng (group_core_skill)
    @ManyToOne
    @JoinColumn(name = "id_group_core")
    private GroupCoreSkill groupCoreSkill;

    // kỹ năng (available_skills)
    @ManyToOne
    @JoinColumn(name = "available_skill_id")
    private AvailableSkill availableSkill;

    // kinh nghiệm (experience)
    @ManyToOne
    @JoinColumn(name = "experience_id")
    private Experience experience;

    // user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
