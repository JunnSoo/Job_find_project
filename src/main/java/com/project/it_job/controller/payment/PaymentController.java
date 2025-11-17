package com.project.it_job.controller.payment;

import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.payment.PaymentRequest;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.payment.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<?> getAll(PageRequestCustom pageRequestCustom) {
        if (pageRequestCustom.getPageNumber() == 0
                && pageRequestCustom.getPageSize() == 0
                && pageRequestCustom.getKeyword() == null
                && pageRequestCustom.getSortBy() == null ){
            return ResponseEntity.ok(BaseResponse.success(paymentService.getAll(),"OK"));
        }

        return ResponseEntity.ok(BaseResponse.success(paymentService.getAllWithPage(pageRequestCustom),"OK"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(BaseResponse.success(paymentService.getById(id), "OK"));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PaymentRequest request) {
        return ResponseEntity.ok(BaseResponse.success(paymentService.create(request), "OK"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody PaymentRequest request) {
        return ResponseEntity.ok(BaseResponse.success(paymentService.update(id, request), "OK"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        paymentService.deleteById(id);
        return ResponseEntity.ok(BaseResponse.success(null, "OK"));
    }
}
