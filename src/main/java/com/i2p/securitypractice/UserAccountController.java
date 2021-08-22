package com.i2p.securitypractice;

import com.i2p.securitypractice.Repository.ConfirmationTokenRepository;
import com.i2p.securitypractice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserAccountController {

    @Autowired
    public UserRepository userRepository;
    @Autowired
    public ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    public EmailService emailService;
    @GetMapping(value = "/register")
    public ModelAndView displayRegistration(ModelAndView modelAndView, UserEntity userEntity){
        modelAndView.addObject("userEntiry",userEntity);
        modelAndView.setViewName("Register");
        return modelAndView;
    }

    @PostMapping(value = "/register")
    public ModelAndView registerUser(ModelAndView modelAndView, UserEntity userEntity){

        UserEntity existingUser = userRepository.findByEmail_idIgnoreCase(userEntity.getEmail_id());

        if(existingUser!=null){
        modelAndView.addObject("Message","This Email already Exists");
        modelAndView.setViewName("error");
        }else{

            userRepository.save(userEntity);
            ConfirmationToken confirmationToken = new ConfirmationToken(userEntity);
            confirmationTokenRepository.save(confirmationToken);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(userEntity.getEmail_id());
            mailMessage.setCc("sooraj.cs17@iba-suk.edu.pk");
            mailMessage.setSubject("Registration Completed");
            mailMessage.setFrom("k3052525@gmail.com");
            mailMessage.setText("To confirm your account, please click here : "
                    +"http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken());

            emailService.sendMail(mailMessage);
            modelAndView.addObject("emailId",userEntity.getEmail_id());

            modelAndView.setViewName("successfulRegistration");
        }
        return modelAndView;
    }
}
