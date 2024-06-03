package be.tftic.java.controllers;

import be.tftic.java.bll.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {
    private final EmailService emailService;

    @GetMapping("/sendEmail")
    public String sendEmail() {
        emailService.sendEmail("antoinegeoris@hotmail.fr", "Email testing from Springboot", "This is a test email");
        return "Email sent succesfully !";
    }
}
