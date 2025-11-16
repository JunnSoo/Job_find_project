package com.project.it_job.service;

public interface EmailService {
    void sendEmail(String to, String subject, String contentText);
}
