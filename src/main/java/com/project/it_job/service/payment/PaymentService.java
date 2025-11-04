package com.project.it_job.service.payment;

import com.project.it_job.dto.payment.PaymentDTO;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.payment.PaymentRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PaymentService {
    List<PaymentDTO> getAll();
    Page<PaymentDTO> getAllWithPage(PageRequestCustom req);
    PaymentDTO getById(Integer id);
    PaymentDTO create(PaymentRequest req);
    PaymentDTO update(Integer id, PaymentRequest req);
    PaymentDTO deleteById(Integer id);
}
