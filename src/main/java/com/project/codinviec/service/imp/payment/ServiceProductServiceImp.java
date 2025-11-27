package com.project.codinviec.service.imp.payment;

import com.project.codinviec.dto.payment.ServiceProductDTO;
import com.project.codinviec.entity.payment.ServiceProduct;
import com.project.codinviec.exception.common.NotFoundIdExceptionHandler;
import com.project.codinviec.mapper.payment.ServiceProductMapper;
import com.project.codinviec.repository.JobRepository;
import com.project.codinviec.repository.auth.UserRepository;
import com.project.codinviec.repository.payment.ServiceProductRepository;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.payment.ServiceProductRequest;
import com.project.codinviec.service.payment.ServiceProductService;
import com.project.codinviec.specification.payment.ServiceProductSpecification;
import com.project.codinviec.util.helper.PageCustomHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceProductServiceImp implements ServiceProductService {

    private final ServiceProductRepository serviceProductRepository;
    private final ServiceProductMapper serviceProductMapper;
    private final ServiceProductSpecification serviceProductSpecification;
    private final PageCustomHelper pageCustomHelper;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    @Override
    public List<ServiceProductDTO> getAll() {
        return serviceProductMapper.serviceProductDTOList(serviceProductRepository.findAll());
    }

    @Override
    @Transactional
    public Page<ServiceProductDTO> getAllWithPage(PageRequestCustom req) {
        PageRequestCustom pageRequestValidate = pageCustomHelper.validatePageCustom(req);

        Specification<ServiceProduct> spec = serviceProductSpecification.searchByName(pageRequestValidate.getKeyword());

        Sort sort = switch (pageRequestValidate.getSortBy()) {
            case "nameAsc" -> Sort.by(Sort.Direction.ASC, "name");
            case "nameDesc" -> Sort.by(Sort.Direction.DESC, "name");
            case "descriptionAsc" -> Sort.by(Sort.Direction.ASC, "description");
            case "descriptionDesc" -> Sort.by(Sort.Direction.DESC, "description");
            case "priceAsc" -> Sort.by(Sort.Direction.ASC, "price");
            case "priceDesc" -> Sort.by(Sort.Direction.DESC, "price");
            default -> Sort.by(Sort.Direction.ASC, "id");
        };

        Pageable pageable = PageRequest.of(pageRequestValidate.getPageNumber() - 1, pageRequestValidate.getPageSize(), sort);

        return serviceProductRepository.findAll(spec,pageable)
                .map(serviceProductMapper::serviceProductDTO);
    }

    @Override
    public ServiceProductDTO getById(Integer id) {
        ServiceProduct serviceProduct = serviceProductRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Id"));
        return serviceProductMapper.serviceProductDTO(serviceProduct);
    }

    @Override
    @Transactional
    public ServiceProductDTO create(ServiceProductRequest req) {

        userRepository.findById(req.getUserId())
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Id User"));

        jobRepository.findById(req.getJobId())
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Id Job"));

        ServiceProduct serviceProduct = serviceProductMapper.saveServiceProduct(req);
        return serviceProductMapper.serviceProductDTO(serviceProductRepository.save(serviceProduct));
    }

    @Override
    @Transactional
    public ServiceProductDTO update(Integer id, ServiceProductRequest req) {
        ServiceProduct serviceProduct = serviceProductRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Id"));
        serviceProductMapper.updateServiceProduct(serviceProduct, req);
        return serviceProductMapper.serviceProductDTO(serviceProductRepository.save(serviceProduct));
    }

    @Override
    @Transactional
    public ServiceProductDTO deleteById(Integer id) {
        ServiceProduct serviceProduct = serviceProductRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Id"));
        serviceProductRepository.delete(serviceProduct);
        return serviceProductMapper.serviceProductDTO(serviceProduct);
    }
}
