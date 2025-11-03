package com.project.it_job.mapper.payment;

import com.project.it_job.dto.payment.PaymentMethodDTO;
import com.project.it_job.entity.payment.PaymentMethod;
import com.project.it_job.request.payment.PaymentMethodRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class PaymentMethodMapper {
    public PaymentMethodDTO paymentMethodDTO(PaymentMethod paymentMethod) {
        return PaymentMethodDTO.builder()
                .id(paymentMethod.getId())
                .name(paymentMethod.getName())
                .build();
    }

    public PaymentMethod savePaymenMethodMapper(PaymentMethodRequest  paymentMethodRequest) {
        if(paymentMethodRequest == null) return null;
        return PaymentMethod.builder()
                .name(paymentMethodRequest.getName())
                .createdDate(LocalDateTime.now())
                .build();
    }

    public void updatePaymenMethodMapper(PaymentMethod paymentMethod, PaymentMethodRequest  paymentMethodRequest) {
        if (paymentMethodRequest == null || paymentMethod == null) return;

        paymentMethod.setName(paymentMethodRequest.getName());
    }

    public List<PaymentMethodDTO> paymentMethodDTOList(List<PaymentMethod> paymentMethodsList) { return paymentMethodsList.stream().map(this::paymentMethodDTO).toList();}
}
