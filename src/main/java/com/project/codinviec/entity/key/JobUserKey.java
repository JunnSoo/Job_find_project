package com.project.codinviec.entity.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobUserKey {
    @Column(name = "job_id")
    private int jobId;

    @Column(name = "user_id")
    private String userId;
}
