package com.project.it_job.entity.auth;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name ="role")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "CHAR(36)", updatable = false, nullable = false)
    private String id;

    @JoinColumn(name = "role_name")
    private String roleName;

    @JoinColumn(name = "description")
    private String description;

    @JoinColumn(name = "created_date")
    private LocalDateTime createdDate;

    @JoinColumn(name = "updated_date")
    private LocalDateTime updatedDate;

}
