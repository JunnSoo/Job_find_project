package com.project.codinviec.controller.payment;

import com.project.codinviec.dto.vnpay.VNPAYCallBackResponseDTO;
import com.project.codinviec.dto.vnpay.VNPAYPaymentResponseDTO;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.payment.PaymentRequest;
import com.project.codinviec.response.BaseResponse;
import com.project.codinviec.service.payment.PaymentService;
import com.project.codinviec.service.payment.VNPAYService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final VNPAYService vnpayService;

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


    @PostMapping("/vnpay/create")
    public ResponseEntity<?> createPayment(
            @Valid @RequestBody PaymentRequest request,
            HttpServletRequest httpRequest) {

        // Gọi service để tạo URL thanh toán
        VNPAYPaymentResponseDTO response = vnpayService.createPaymentUrl(request, httpRequest);

        // Trả về response thành công
        return ResponseEntity.ok(BaseResponse.success(response, "Tạo URL thanh toán thành công"));
    }


    @GetMapping("/vnpay/callback")
    public ResponseEntity<?> paymentCallback(HttpServletRequest request) {

        // Bước 1: Lấy tất cả query parameters từ VNPAY
        Map<String, String> params = new HashMap<>();

        // Lặp qua tất cả parameter names
        for (String paramName : request.getParameterMap().keySet()) {
            // Lấy giá trị đầu tiên của mỗi parameter
            // (VNPAY không gửi duplicate parameters)
            String paramValue = request.getParameter(paramName);
            params.put(paramName, paramValue);
        }

        // Bước 2: Gọi service để xử lý callback
        VNPAYCallBackResponseDTO response = vnpayService.handleCallBack(params);

        // Bước 3: Xử lý response dựa trên code
        if ("00".equals(response.getCode())) {
            // Thanh toán thành công
            return ResponseEntity.ok(BaseResponse.success(response, "Thanh toán thành công"));
        } else {
            // Thanh toán thất bại hoặc lỗi
            return ResponseEntity.ok(BaseResponse.error(
                    "Thanh toán thất bại: " + response.getMessage(),
                    org.springframework.http.HttpStatus.BAD_REQUEST
            ));
        }
    }





}
