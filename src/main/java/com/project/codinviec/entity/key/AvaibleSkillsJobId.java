package com.project.codinviec.entity.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Embeddable
public class AvaibleSkillsJobId {
    @NotNull
    @Column(name = "id_job", nullable = false)
    private Integer idJob;

    @NotNull
    @Column(name = "id_available_skills", nullable = false)
    private Integer idAvailableSkills;
}
