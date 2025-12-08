package com.project.codinviec.util.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
public class VNPayHelper {

    /**
     * Tạo chữ ký (hash) từ tất cả các tham số để bảo mật giao dịch
     *
     * @param fields Map chứa tất cả các tham số cần hash
     * @param vnp_HashSecret Secret key từ VNPAY
     * @return Chuỗi hash đã được mã hóa
     */
    public String hashAllFields(Map<String, String> fields, String vnp_HashSecret) {
        // Bước 1: Sắp xếp các key theo thứ tự alphabet
        // VNPAY yêu cầu các tham số phải được sắp xếp để đảm bảo tính nhất quán
        List<String> fieldNames = new ArrayList<>(fields.keySet());
        Collections.sort(fieldNames);

        // Bước 2: Tạo chuỗi query string từ các field đã sắp xếp
        // Format: key1=value1&key2=value2&key3=value3
        StringBuilder hashData = new StringBuilder();
        Iterator<String> itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = itr.next();
            String fieldValue = fields.get(fieldName);

            // Chỉ thêm field nếu có giá trị
            if ((fieldValue != null) && (!fieldValue.isEmpty())) {
                hashData.append(fieldName);
                hashData.append('=');
                // URL encode giá trị để xử lý các ký tự đặc biệt
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                if (itr.hasNext()) {
                    hashData.append('&');
                }
            }
        }

        // Bước 3: Hash chuỗi vừa tạo bằng HMAC SHA512
        return hmacSHA512(vnp_HashSecret, hashData.toString());
    }

    /**
     * Mã hóa dữ liệu bằng thuật toán HMAC SHA512
     * Đây là phương thức bảo mật mà VNPAY sử dụng
     *
     * @param key Secret key từ VNPAY
     * @param data Dữ liệu cần mã hóa
     * @return Chuỗi hash dạng hexadecimal (ví dụ: "a1b2c3d4...")
     */
    public String hmacSHA512(String key, String data) {
        try {
            if (key == null || data == null) {
                throw new NullPointerException("Key và data không được null");
            }

            // Khởi tạo Mac với thuật toán HmacSHA512
            Mac hmac512 = Mac.getInstance("HmacSHA512");

            // Chuyển key thành bytes
            byte[] hmacKeyBytes = key.getBytes();
            SecretKeySpec secretKey = new SecretKeySpec(hmacKeyBytes, "HmacSHA512");

            // Khởi tạo Mac với secret key
            hmac512.init(secretKey);

            // Chuyển data thành bytes và hash
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            byte[] result = hmac512.doFinal(dataBytes);

            // Chuyển kết quả từ bytes sang chuỗi hexadecimal
            StringBuilder sb = new StringBuilder(2 * result.length);
            for (byte b : result) {
                // Format mỗi byte thành 2 ký tự hex (ví dụ: 255 -> "ff")
                sb.append(String.format("%02x", b & 0xff));
            }

            return sb.toString();
        } catch (Exception ex) {
            // Log lỗi và trả về chuỗi rỗng
            return "";
        }
    }

    /**
     * Lấy địa chỉ IP thực của client
     * VNPAY cần IP để xác thực và bảo mật
     *
     * @param request HttpServletRequest từ client
     * @return Địa chỉ IP của client
     */
    public String getIpAddress(HttpServletRequest request) {
        // Thử lấy IP từ các header phổ biến
        // X-Forwarded-For: Header khi request đi qua proxy/load balancer
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            // X-Real-IP: Header từ Nginx
            ipAddress = request.getHeader("X-Real-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            // Proxy-Client-IP: Header từ proxy
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            // WL-Proxy-Client-IP: Header từ WebLogic
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            // Nếu không có header nào, lấy IP trực tiếp từ request
            ipAddress = request.getRemoteAddr();
        }

        // Nếu có nhiều IP (qua proxy), lấy IP đầu tiên
        if (ipAddress != null && ipAddress.contains(",")) {
            ipAddress = ipAddress.split(",")[0].trim();
        }

        return ipAddress;
    }

    /**
     * Tạo mã giao dịch ngẫu nhiên
     * VNPAY yêu cầu mỗi giao dịch phải có mã duy nhất
     *
     * @param len Độ dài của mã (số ký tự)
     * @return Chuỗi số ngẫu nhiên
     */
    public String getRandomNumber(int len) {
        Random rnd = new Random();
        String chars = "0123456789";
        StringBuilder sb = new StringBuilder(len);

        // Tạo chuỗi ngẫu nhiên với độ dài len
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }

        return sb.toString();
    }

}
