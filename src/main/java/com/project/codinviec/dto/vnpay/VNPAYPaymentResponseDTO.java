package com.project.codinviec.dto.vnpay;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VNPAYPaymentResponseDTO {
    private String paymentUrl;
    private String vnpTxnRef;
}
