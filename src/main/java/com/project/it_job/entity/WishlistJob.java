package com.project.it_job.entity;

import com.project.it_job.keyentity.WishlistJobKey;
import com.project.it_job.entity.auth.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "wishlist_job")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WishlistJob {
    @EmbeddedId
    private WishlistJobKey wishlistJobKey;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("jobId")
    @JoinColumn(name = "job_id")
    private Job job;

}
