package be.tftic.java.bll.services;

public interface EmailService {
    void sendEmail(String to, String templateName, String subject, String context);
}
