package com.project.it_job.service.payment;

import com.project.it_job.dto.payment.ServiceProductDTO;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.payment.ServiceProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ServiceProductService {
    List<ServiceProductDTO> getAll();
    Page<ServiceProductDTO> getAllWithPage(PageRequestCustom req);
    ServiceProductDTO getById(Integer id);
    ServiceProductDTO create(ServiceProductRequest req);
    ServiceProductDTO update(Integer id, ServiceProductRequest req);
    ServiceProductDTO deleteById(Integer id);
}
