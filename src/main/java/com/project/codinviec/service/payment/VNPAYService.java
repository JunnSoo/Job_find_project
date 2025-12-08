package com.project.codinviec.service.payment;

import com.project.codinviec.dto.vnpay.VNPAYCallBackResponseDTO;
import com.project.codinviec.dto.vnpay.VNPAYPaymentResponseDTO;
import com.project.codinviec.request.payment.PaymentRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

public interface VNPAYService {
    VNPAYPaymentResponseDTO createPaymentUrl(PaymentRequest paymentRequest, HttpServletRequest request);
    VNPAYCallBackResponseDTO handleCallBack(Map<String, String> params);
}
