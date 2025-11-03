package com.project.it_job.service.imp.payment;

import com.project.it_job.dto.payment.PaymentMethodDTO;
import com.project.it_job.entity.payment.PaymentMethod;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.payment.PaymentMethodMapper;
import com.project.it_job.repository.payment.PaymentMethodRepository;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.payment.PaymentMethodRequest;
import com.project.it_job.service.payment.PaymentMethodService;
import com.project.it_job.specification.payment.PaymentMethodSpecification;
import com.project.it_job.util.PageCustomHelpper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentMethodServiceImp implements PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentMethodMapper paymentMethodMapper;
    private final PaymentMethodSpecification  paymentMethodSpecification;
    private final PageCustomHelpper  pageCustomHelpper;

    @Override
    public List<PaymentMethodDTO> getAll() {
        return paymentMethodMapper.paymentMethodDTOList(paymentMethodRepository.findAll());
    }

    @Override
    public Page<PaymentMethodDTO> getAllWithPage(PageRequestCustom req) {
        PageRequestCustom pageRequestValidate = pageCustomHelpper.validatePageCustom(req);

        Specification<PaymentMethod> spec = paymentMethodSpecification.searchByName(pageRequestValidate.getKeyword());

        Sort sort = switch (pageRequestValidate.getSortBy()) {
            case "nameAsc" -> Sort.by(Sort.Direction.ASC, "name");
            case "nameDesc" -> Sort.by(Sort.Direction.DESC, "name");
            default -> Sort.by(Sort.Direction.ASC, "id");
        };

        Pageable pageable = PageRequest.of(pageRequestValidate.getPageNumber() - 1, pageRequestValidate.getPageSize(), sort);

        return paymentMethodRepository.findAll(spec,pageable)
                .map(paymentMethodMapper::paymentMethodDTO);
    }

    @Override
    public PaymentMethodDTO getById(Integer id) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id"));

        return paymentMethodMapper.paymentMethodDTO(paymentMethod);
    }

    @Override
    public PaymentMethodDTO create(PaymentMethodRequest req) {
        PaymentMethod paymentMethod = paymentMethodMapper.savePaymenMethodMapper(req);

        return paymentMethodMapper.paymentMethodDTO(paymentMethodRepository.save(paymentMethod));
    }

    @Override
    public PaymentMethodDTO update(Integer id, PaymentMethodRequest req) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id"));

        paymentMethodMapper.updatePaymenMethodMapper(paymentMethod, req);

        return paymentMethodMapper.paymentMethodDTO(paymentMethodRepository.save(paymentMethod));
    }

    @Override
    public PaymentMethodDTO deleteById(Integer id) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(id)
                        .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id"));
        paymentMethodRepository.deleteById(id);
        return paymentMethodMapper.paymentMethodDTO(paymentMethod);
    }
}
