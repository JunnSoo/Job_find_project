package com.project.it_job.block;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserBlock {
    private String email;
    private int countLoginError;
    private boolean isBlocked;
    private String createdAt;
    private String expireTime;
}
