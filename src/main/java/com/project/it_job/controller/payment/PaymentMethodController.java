package com.project.it_job.controller.payment;

import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.payment.PaymentMethodRequest;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.payment.PaymentMethodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment-method")
@RequiredArgsConstructor
public class PaymentMethodController {
    private final PaymentMethodService paymentMethodService;

    @GetMapping
    public ResponseEntity<?> getAll(PageRequestCustom pageRequestCustom) {
        if (pageRequestCustom.getPageNumber() == 0
                && pageRequestCustom.getPageSize() == 0
                && pageRequestCustom.getKeyword() == null
                && pageRequestCustom.getSortBy() == null ){
            return ResponseEntity.ok(BaseResponse.success(paymentMethodService.getAll(),"OK"));
        }

        return ResponseEntity.ok(BaseResponse.success(paymentMethodService.getAllWithPage(pageRequestCustom),"OK"));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PaymentMethodRequest request) {
        return ResponseEntity.ok(BaseResponse.success(paymentMethodService.create(request), "OK"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody PaymentMethodRequest request) {
        return ResponseEntity.ok(BaseResponse.success(paymentMethodService.update(id, request), "OK"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        paymentMethodService.deleteById(id);
        return ResponseEntity.ok(BaseResponse.success(null, "OK"));
    }
}
