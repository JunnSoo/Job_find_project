package com.project.codinviec.dto.vnpay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VNPAYCallBackResponseDTO {

    private String code;

    private String message;

    private Integer paymentId;
}