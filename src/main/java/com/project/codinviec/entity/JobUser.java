package com.project.codinviec.entity;

import com.project.codinviec.entity.auth.User;
import com.project.codinviec.entity.key.JobUserKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "job_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobUser {
    @EmbeddedId
    private JobUserKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("jobId")
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;
}
