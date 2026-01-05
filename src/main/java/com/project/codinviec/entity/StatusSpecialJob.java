package com.project.codinviec.entity;

import com.project.codinviec.entity.key.StatusSpecialJobId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "status_special_job")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class StatusSpecialJob {
    @EmbeddedId
    private StatusSpecialJobId id;

    @MapsId("idJob")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_job", nullable = false)
    private Job job;

    @MapsId("idStatusSpecial")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_status_special", nullable = false)
    private StatusSpecial statusSpecial;
}