package com.project.codinviec.mapper.payment;

import com.project.codinviec.dto.payment.PaymentDTO;
import com.project.codinviec.entity.auth.User;
import com.project.codinviec.entity.payment.Payment;
import com.project.codinviec.entity.payment.PaymentMethod;
import com.project.codinviec.entity.payment.PaymentStatus;
import com.project.codinviec.entity.payment.ServiceProduct;
import com.project.codinviec.request.payment.PaymentRequest;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;


@Component
public class PaymentMapper {
    public PaymentDTO paymentDTO(Payment payment) {
       return PaymentDTO.builder()
               .id(payment.getId())
               .title(payment.getTitle())
               .description(payment.getDescription())
               .paymentMethod(payment.getPaymentMethod().getName())
               .status(payment.getPaymentStatus().getName())
               .serviceProduct(payment.getServiceProduct().getName())
               .user(payment.getUser().getFirstName() +  " " + payment.getUser().getLastName())
               .createdDate(payment.getCreatedDate())
               .updatedDate(payment.getUpdatedDate())
               .build();
    }

    public Payment savePayment(PaymentRequest  paymentRequest) {
        return Payment.builder()
                .title(paymentRequest.getTitle())
                .description(paymentRequest.getDescription())
                .paymentMethod(PaymentMethod.builder()
                        .id(paymentRequest.getPaymentMethodId())
                        .build())
                .paymentStatus(PaymentStatus.builder()
                        .id(paymentRequest.getStatusId())
                        .build())
                .serviceProduct(ServiceProduct.builder()
                        .id(paymentRequest.getServiceProductId())
                        .build())
                .user(User.builder()
                        .id(paymentRequest.getUserId())
                        .build())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
    }

    public void updatePayment(Payment payment, PaymentRequest req) {
        if (payment == null || req == null) return;

        if (req.getTitle() != null) {
            payment.setTitle(req.getTitle());
        }

        if (req.getDescription() != null) {
            payment.setDescription(req.getDescription());
        }

        if (req.getPaymentMethodId() != 0) {
            payment.setPaymentMethod(PaymentMethod.builder()
                    .id(req.getPaymentMethodId())
                    .build());
        }

        if (req.getStatusId() != 0) {
            payment.setPaymentStatus(PaymentStatus.builder()
                    .id(req.getStatusId())
                    .build());
        }

        if (req.getServiceProductId() != 0) {
            payment.setServiceProduct(ServiceProduct.builder()
                    .id(req.getServiceProductId())
                    .build());
        }

        if (req.getUserId() != null) {
            payment.setUser(User.builder()
                    .id(req.getUserId())
                    .build());
        }

        payment.setCreatedDate(payment.getCreatedDate());
        payment.setUpdatedDate(LocalDateTime.now());
    }


    public List<PaymentDTO> paymentDTOList(List<Payment> paymentList) { return paymentList.stream().map(this::paymentDTO).toList();}

}
