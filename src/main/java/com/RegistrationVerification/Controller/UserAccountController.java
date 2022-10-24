package com.RegistrationVerification.Controller;

import com.RegistrationVerification.Model.UserModel;
import com.RegistrationVerification.Repository.ConformationTokenRepository;
import com.RegistrationVerification.Repository.UserRepository;
import com.RegistrationVerification.Service.EmailService;
import com.RegistrationVerification.token.ConformationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserAccountController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConformationTokenRepository conformationTokenRepository;
    @Autowired
    private EmailService emailService;
    @RequestMapping(value="/register", method = RequestMethod.GET)
    public ModelAndView displayRegistration(ModelAndView modelAndView, UserModel userModel)
    {
        modelAndView.addObject("userModel", userModel);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ModelAndView registerUser(ModelAndView modelAndView, UserModel userModel)
    {
        UserModel existingUser = userRepository.findByEmailIdIgnoreCase(userModel.getEmailId());
        if(existingUser != null)
        {
            modelAndView.addObject("message","This email already exists!");
            modelAndView.setViewName("error");
        }
        else
        {
            userRepository.save(userModel);

            ConformationToken conformationToken = new ConformationToken(userModel);

            conformationTokenRepository.save(conformationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(userModel.getEmailId());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("YOUR EMAIL ADDRESS");
            mailMessage.setText("To confirm your account, please click here : "
                    +"http://localhost:8080/confirm-account?token="+conformationToken.getConformationToken());

            emailService.sendEmail(mailMessage);

            modelAndView.addObject("emailId", userModel.getEmailId());

            modelAndView.setViewName("successfulRegisteration");
        }

        return modelAndView;
    }


    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView conformUserAccount(ModelAndView modelAndView, @RequestParam("token")String conformationToken)
    {
        ConformationToken token = conformationTokenRepository.findByConformationToken(conformationToken);

        if(token != null)
        {
            UserModel user = userRepository.findByEmailIdIgnoreCase(token.getUserModel().getEmailId());
            user.setEnabled(true);
            userRepository.save(user);
            modelAndView.setViewName("accountVerified");
        }
        else
        {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }
}