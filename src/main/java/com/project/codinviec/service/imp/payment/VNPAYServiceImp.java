package com.project.codinviec.service.imp.payment;

import com.project.codinviec.config.VNPAYConfig;
import com.project.codinviec.dto.vnpay.VNPAYCallBackResponseDTO;
import com.project.codinviec.dto.vnpay.VNPAYPaymentResponseDTO;
import com.project.codinviec.entity.auth.User;
import com.project.codinviec.entity.payment.Payment;
import com.project.codinviec.entity.payment.PaymentMethod;
import com.project.codinviec.entity.payment.PaymentStatus;
import com.project.codinviec.entity.payment.ServiceProduct;
import com.project.codinviec.exception.common.NotFoundIdExceptionHandler;
import com.project.codinviec.repository.auth.UserRepository;
import com.project.codinviec.repository.payment.PaymentMethodRepository;
import com.project.codinviec.repository.payment.PaymentRepository;
import com.project.codinviec.repository.payment.PaymentStatusRepository;
import com.project.codinviec.repository.payment.ServiceProductRepository;
import com.project.codinviec.request.payment.PaymentRequest;
import com.project.codinviec.service.payment.VNPAYService;
import com.project.codinviec.util.security.VNPayHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;


@Service
@RequiredArgsConstructor
public class VNPAYServiceImp implements VNPAYService {
    private final VNPAYConfig vnpayConfig;
    private final VNPayHelper vnPayHelper;
    private final PaymentRepository paymentRepository;
    private final ServiceProductRepository serviceProductRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentStatusRepository paymentStatusRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public VNPAYPaymentResponseDTO createPaymentUrl(PaymentRequest paymentRequest, HttpServletRequest httpRequest) {
        // Bước 1: Validate ServiceProduct
        ServiceProduct serviceProduct = serviceProductRepository.findById(paymentRequest.getServiceProductId())
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Service Product"));

        // Bước 2: Validate User
        User user = userRepository.findById(paymentRequest.getUserId())
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy User"));

        // Bước 3: Tìm PaymentMethod VNPAY (id = 9 theo database)
        PaymentMethod vnpayMethod = paymentMethodRepository.findByNameIgnoreCase("VNPAY")
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Payment Method VNPAY"));

        // Bước 4: Tìm PaymentStatus PENDING (id = 1 theo database)
        PaymentStatus pendingStatus = paymentStatusRepository.findByNameIgnoreCase("Pending")
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Payment Status PENDING"));

        // Bước 5: Tạo mã giao dịch ngẫu nhiên (8 chữ số)
        String randomNumber = vnPayHelper.getRandomNumber(8);

        // Bước 6: Tạo orderInfo (mô tả đơn hàng)
        String orderInfo = paymentRequest.getDescription() != null && !paymentRequest.getDescription().isEmpty()
                ? paymentRequest.getDescription()
                : "Thanh toán đơn hàng: " + serviceProduct.getName();

        // Bước 7: Tạo Payment record với status PENDING
        Payment payment = Payment.builder()
                .title("Thanh toán VNPAY - " + serviceProduct.getName())
                .description(orderInfo)
                .paymentMethod(vnpayMethod)
                .paymentStatus(pendingStatus)
                .serviceProduct(serviceProduct)
                .user(user)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        // Bước 8: Lưu Payment để lấy ID
        Payment savedPayment = paymentRepository.save(payment);

        // Bước 9: Tạo vnp_TxnRef với format: paymentId_randomNumber
        // Ví dụ: "123_45678901" - giúp dễ dàng tra cứu payment sau này
        String vnp_TxnRef = savedPayment.getId() + "_" + randomNumber;


        // Bước 10: Build URL thanh toán VNPAY
        String paymentUrl = buildPaymentUrl(
                vnp_TxnRef,
                serviceProduct.getPrice(),
                orderInfo,
                "other",
                httpRequest
        );

        // Bước 11: Trả về response
        return VNPAYPaymentResponseDTO.builder()
                .paymentUrl(paymentUrl)
                .vnpTxnRef(vnp_TxnRef)
                .build();
    }

    @Override
    public VNPAYCallBackResponseDTO handleCallBack(Map<String, String> params) {
        // Bước 1: Lấy các tham số quan trọng từ VNPAY callback
        String vnp_ResponseCode = params.get("vnp_ResponseCode");
        String vnp_TransactionStatus = params.get("vnp_TransactionStatus");
        String vnp_TxnRef = params.get("vnp_TxnRef");
        String vnp_Amount = params.get("vnp_Amount");
//        String vnp_TransactionNo = params.get("vnp_TransactionNo");
        String vnp_SecureHash = params.get("vnp_SecureHash");

        // Bước 2: Xác thực chữ ký (checksum)
        // Tạo bản sao của params và loại bỏ vnp_SecureHash để tính toán lại
        Map<String, String> vnp_Params = new HashMap<>(params);
        vnp_Params.remove("vnp_SecureHash");
        vnp_Params.remove("vnp_SecureHashType"); // Loại bỏ nếu có

        // Tính toán lại chữ ký
        String checkSum = vnPayHelper.hashAllFields(vnp_Params, vnpayConfig.getHashSecret());

        // So sánh chữ ký
        if (!checkSum.equals(vnp_SecureHash)) {
            return VNPAYCallBackResponseDTO.builder()
                    .code("97")
                    .message("Checksum failed - Chữ ký không hợp lệ")
                    .build();
        }

        // Bước 3: Lấy paymentId từ vnp_TxnRef
        // Format: "paymentId_randomNumber" (ví dụ: "123_45678901")
        String[] txnRefParts = vnp_TxnRef.split("_");
        if (txnRefParts.length < 1) {
            return VNPAYCallBackResponseDTO.builder()
                    .code("01")
                    .message("Invalid transaction reference - Mã giao dịch không hợp lệ")
                    .build();
        }

        Integer paymentId;
        try {
            paymentId = Integer.parseInt(txnRefParts[0]);
        } catch (NumberFormatException e) {
            return VNPAYCallBackResponseDTO.builder()
                    .code("01")
                    .message("Invalid transaction reference - Mã giao dịch không hợp lệ")
                    .build();
        }

        // Bước 4: Tìm Payment record
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Payment với ID: " + paymentId));

        // Bước 5: Kiểm tra số tiền có khớp không
        // VNPAY trả về số tiền đã nhân 100, nên cần so sánh với amount * 100
        Long expectedAmount = (long)(payment.getServiceProduct().getPrice() * 100);
        if (!expectedAmount.toString().equals(vnp_Amount)) {
            return VNPAYCallBackResponseDTO.builder()
                    .code("04")
                    .message("Invalid amount - Số tiền không khớp")
                    .paymentId(paymentId)
                    .build();
        }

        // Bước 6: Xử lý kết quả thanh toán
        // "00" = thành công
        if ("00".equals(vnp_ResponseCode) && "00".equals(vnp_TransactionStatus)) {
            // Thanh toán thành công
            PaymentStatus successStatus = paymentStatusRepository.findByNameIgnoreCase("Completed") // COMPLETED
                    .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Payment Status COMPLETED"));

            payment.setPaymentStatus(successStatus);
            payment.setUpdatedDate(LocalDateTime.now());
            paymentRepository.save(payment);

            return VNPAYCallBackResponseDTO.builder()
                    .code("00")
                    .message("Success - Thanh toán thành công")
                    .paymentId(paymentId)
                    .build();
        } else {
            // Thanh toán thất bại
            PaymentStatus failedStatus = paymentStatusRepository.findByNameIgnoreCase("Failed") // FAILED
                    .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Payment Status FAILED"));

            payment.setPaymentStatus(failedStatus);
            payment.setUpdatedDate(LocalDateTime.now());
            paymentRepository.save(payment);

            return VNPAYCallBackResponseDTO.builder()
                    .code(vnp_ResponseCode != null ? vnp_ResponseCode : "99")
                    .message("Payment failed - Thanh toán thất bại")
                    .paymentId(paymentId)
                    .build();
        }
    }

    public String buildPaymentUrl(String vnp_TxnRef, Double amount, String orderInfo,
                                  String orderType, HttpServletRequest request) {

        // Các tham số bắt buộc theo VNPAY API
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String vnp_TmnCode = vnpayConfig.getTmnCode();

        // VNPAY yêu cầu số tiền phải nhân 100 (ví dụ: 500000 VND -> 50000000)
        String vnp_Amount = String.valueOf((long) (amount * 100));
        String vnp_CurrCode = "VND";
        String vnp_IpAddr = vnPayHelper.getIpAddress(request);
        String vnp_Locale = "vn"; // "vn" hoặc "en"
        String vnp_ReturnUrl = vnpayConfig.getReturnUrl();
        String vnp_OrderType = orderType != null && !orderType.isEmpty() ? orderType : "other";

        // Tạo Map chứa tất cả tham số
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", vnp_Amount);
        vnp_Params.put("vnp_CurrCode", vnp_CurrCode);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", orderInfo);
        vnp_Params.put("vnp_OrderType", vnp_OrderType);
        vnp_Params.put("vnp_Locale", vnp_Locale);
        vnp_Params.put("vnp_ReturnUrl", vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        // Tạo thời gian tạo giao dịch (format: yyyyMMddHHmmss)
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        // Tạo thời gian hết hạn (15 phút sau)
        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        // Tạo chữ ký (hash) để bảo mật
        String vnp_SecureHash = vnPayHelper.hashAllFields(vnp_Params, vnpayConfig.getHashSecret());
        vnp_Params.put("vnp_SecureHash", vnp_SecureHash);

        // Build query string từ Map
        StringBuilder queryUrl = new StringBuilder();
        Iterator<Map.Entry<String, String>> itr = vnp_Params.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<String, String> entry = itr.next();
            queryUrl.append(URLEncoder.encode(entry.getKey(), StandardCharsets.US_ASCII));
            queryUrl.append('=');
            queryUrl.append(URLEncoder.encode(entry.getValue(), StandardCharsets.US_ASCII));
            if (itr.hasNext()) {
                queryUrl.append('&');
            }
        }

        // Trả về URL hoàn chỉnh
        return vnpayConfig.getPayUrl() + "?" + queryUrl;
    }

}
