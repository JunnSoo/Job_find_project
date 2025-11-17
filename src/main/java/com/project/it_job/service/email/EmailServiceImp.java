package com.project.it_job.service.email;

import com.project.it_job.exception.SendEmailFailExceptionHandler;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailServiceImp implements EmailService {

    @Value("${email.key}")
    private String sendGridApiKey;

    @Value("${email.mail}")
    private String sendGridMail;

    @Override
    public void sendEmail(String to, String subject, String contentText) {
        Email from = new Email(sendGridMail); // Tạo mail riêng nha bảo
        Email toEmail = new Email(to);

        Content content = new Content("text/html", contentText);
        Mail mail = new Mail(from, subject, toEmail, content);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            log.info("Email gửi thành công. Status Code: {}", response.getStatusCode());
        } catch (Exception ex) {
            log.error("Lỗi gửi email đến: {}", to, ex);
            throw new SendEmailFailExceptionHandler("Lỗi service send email!");
        }
    }
}
