package com.project.codinviec.service.payment;

import com.project.codinviec.dto.payment.ServiceProductDTO;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.payment.ServiceProductRequest;
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
