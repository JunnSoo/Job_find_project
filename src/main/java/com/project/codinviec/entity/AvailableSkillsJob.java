package com.project.codinviec.entity;

import com.project.codinviec.entity.key.AvaibleSkillsJobId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "available_skills_job")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AvailableSkillsJob {
    @EmbeddedId
    private AvaibleSkillsJobId avaibleSkillsJobId;

    @MapsId("idJob")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_job", nullable = false)
    private Job job;

    @MapsId("idAvailableSkills")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_available_skills", nullable = false)
    private AvailableSkill availableSkill;
}
