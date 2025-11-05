package com.project.it_job.request.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private String title;
    private String description;
    private Integer paymentMethodId;
    private Integer statusId;
    private Integer serviceProductId;
    private String userId;
}
