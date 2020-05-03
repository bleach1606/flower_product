package ptit.edu.btl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ptit.edu.btl.service.EmailService;

@RestController
@RequestMapping("/email")
public class MailController extends BaseController{

    @Autowired
    private EmailService emailService;

    @GetMapping(value = "/sendmail")
    public String sendmail() {
        emailService.sendMail("boyzone.km1997@gmail.com", "Test Subject", "Test mail");
        return "emailsent";
    }
}
