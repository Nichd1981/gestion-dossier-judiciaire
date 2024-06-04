//package be.tftic.java.bll.services.impls;
//
//import be.tftic.java.bll.services.EmailService;
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//import lombok.RequiredArgsConstructor;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.Context;
//
//@RequiredArgsConstructor
//@Service
//public class EmailServiceImpl implements EmailService {
//    private final JavaMailSender javaMailSender;
//    private final TemplateEngine templateEngine;
//
//    public void sendEmail(String to, String templateName, String subject, Context context) {
//        String htmlContent = templateEngine.process("emails/" + templateName, context);
//
//        try {
//            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
//            helper.setFrom("info.gdj.app@gmail.com");
//            helper.setTo(to);
//            helper.setSubject(subject);
//            helper.setText(htmlContent, true);
//
//            javaMailSender.send(mimeMessage);
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//
//
//        //        SimpleMailMessage message = new SimpleMailMessage() ;
////
////        message.setFrom("info.gdj.app@gmail.com");
////        message.setTo(to);
////        message.setSubject(subject);
////        message.setText(body);
////
////        mailSender.send(message);
//    }
//}