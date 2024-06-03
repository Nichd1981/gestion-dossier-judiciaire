package be.tftic.java.bll.services;

public interface EmailService {
    void sendEmail(String to, String subject, String text);
}
