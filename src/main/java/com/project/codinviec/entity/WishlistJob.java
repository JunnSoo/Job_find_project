package com.project.codinviec.entity;


import com.project.codinviec.entity.auth.User;
import com.project.codinviec.entity.key.WishlistJobKey;
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
