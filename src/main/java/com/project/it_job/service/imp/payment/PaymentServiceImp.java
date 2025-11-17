package com.project.it_job.service.imp.payment;

import com.project.it_job.dto.payment.PaymentDTO;
import com.project.it_job.entity.payment.Payment;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.payment.PaymentMapper;
import com.project.it_job.repository.auth.UserRepository;
import com.project.it_job.repository.payment.PaymentMethodRepository;
import com.project.it_job.repository.payment.PaymentRepository;
import com.project.it_job.repository.payment.PaymentStatusRepository;
import com.project.it_job.repository.payment.ServiceProductRepository;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.payment.PaymentRequest;
import com.project.it_job.service.payment.PaymentService;
import com.project.it_job.specification.payment.PaymentSpecification;
import com.project.it_job.util.PageCustomHelpper;
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
public class PaymentServiceImp implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final PageCustomHelpper  pageCustomHelpper;
    private final PaymentSpecification paymentSpecification;
    private final PaymentStatusRepository paymentStatusRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final ServiceProductRepository serviceProductRepository;
    private final UserRepository userRepository;

    @Override
    public List<PaymentDTO> getAll() {
        return paymentMapper.paymentDTOList(paymentRepository.findAll());
    }

    @Override
    public Page<PaymentDTO> getAllWithPage(PageRequestCustom req) {
        PageRequestCustom pageRequestValidate = pageCustomHelpper.validatePageCustom(req);

        Specification<Payment> spec = paymentSpecification.searchByTitle(pageRequestValidate.getKeyword());

        Sort sort = switch (pageRequestValidate.getSortBy()) {
            case "titleAsc" -> Sort.by(Sort.Direction.ASC, "title");
            case "titleDesc" -> Sort.by(Sort.Direction.DESC, "title");
            case "descriptionAsc" -> Sort.by(Sort.Direction.ASC, "description");
            case "descriptionDesc" -> Sort.by(Sort.Direction.DESC, "description");
            case "paymentMethodAsc" -> Sort.by(Sort.Direction.ASC, "paymentMethod");
            case "paymentMethodDesc" -> Sort.by(Sort.Direction.DESC, "paymentMethod");
            case "paymentStatusAsc" -> Sort.by(Sort.Direction.ASC, "paymentStatus");
            case "paymentStatusDesc" -> Sort.by(Sort.Direction.DESC, "paymentStatus");
            case "serviceProductAsc" -> Sort.by(Sort.Direction.ASC, "serviceProduct");
            case "serviceProductDesc" -> Sort.by(Sort.Direction.DESC, "serviceProduct");
            case "userAsc" -> Sort.by(Sort.Direction.ASC, "user");
            case "userDesc" -> Sort.by(Sort.Direction.DESC, "user");
            default -> Sort.by(Sort.Direction.ASC, "id");
        };

        Pageable pageable = PageRequest.of(pageRequestValidate.getPageNumber() - 1,  pageRequestValidate.getPageSize(), sort);

        return paymentRepository.findAll(spec,pageable)
                .map(paymentMapper::paymentDTO);
    }

    @Override
    public PaymentDTO getById(Integer id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Id payment"));

        return paymentMapper.paymentDTO(payment);
    }



    @Override
    @Transactional
    public PaymentDTO create(PaymentRequest req) {

        paymentMethodRepository.findById(req.getPaymentMethodId())
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id PaymentMethod"));

        paymentStatusRepository.findById(req.getStatusId())
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id PaymentStatus"));

        serviceProductRepository.findById(req.getServiceProductId())
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id ServiceProduct"));

        // Thay thế jwt sau này
        userRepository.findById(req.getUserId())
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id User"));

        Payment payment = paymentMapper.savePayment(req);
        return paymentMapper.paymentDTO(paymentRepository.save(payment));

    }

    @Override
    @Transactional
    public PaymentDTO update(Integer id, PaymentRequest req) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Id Payment"));

        if (req.getPaymentMethodId() != null) {
            paymentMethodRepository.findById(req.getPaymentMethodId())
                    .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id PaymentMethod"));
        }

        if (req.getStatusId() != null) {
            paymentStatusRepository.findById(req.getStatusId())
                    .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id PaymentStatus"));
        }

        if (req.getServiceProductId() != null) {
            serviceProductRepository.findById(req.getServiceProductId())
                    .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id ServiceProduct"));
        }

        if (req.getUserId() != null) {
            userRepository.findById(req.getUserId())
                    .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id User"));
        }


        paymentMapper.updatePayment(payment,req);
        return paymentMapper.paymentDTO(paymentRepository.save(payment));
    }

    @Override
    @Transactional
    public PaymentDTO deleteById(Integer id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Id Payment"));
        paymentRepository.delete(payment);
        return paymentMapper.paymentDTO(payment);
    }
}
