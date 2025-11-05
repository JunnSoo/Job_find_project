package com.project.it_job.service.payment;


import com.project.it_job.dto.payment.PaymentMethodDTO;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.payment.PaymentMethodRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PaymentMethodService {
    List<PaymentMethodDTO> getAll();
    Page<PaymentMethodDTO> getAllWithPage(PageRequestCustom req);
    PaymentMethodDTO getById(Integer id);
    PaymentMethodDTO create(PaymentMethodRequest req);
    PaymentMethodDTO update(Integer id, PaymentMethodRequest req);
    PaymentMethodDTO deleteById(Integer id);
}
