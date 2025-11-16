package com.project.it_job.service.imp;

import com.project.it_job.exception.SendEmailFailException;
import com.project.it_job.service.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImp implements EmailService {
    @Value("${email.key}")
    private String sendGridApiKey;

    @Override
    public void sendEmail(String to, String subject, String contentText) {
        Email from = new Email("trungphuntp@gmail.com"); // Tạo mail riêng nha bảo
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

            System.out.println("Status Code: " + response.getStatusCode());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SendEmailFailException("Lỗi service send email!");
        }
    }
}
