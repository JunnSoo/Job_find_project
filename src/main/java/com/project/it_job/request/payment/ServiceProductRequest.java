package com.project.it_job.request.payment;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceProductRequest {
    private String name;
    private String description;
    private double price;
    private String images;
    private String userId;
    private int jobId;
}
