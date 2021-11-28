package com.example.travel.controllers.user;

import com.example.travel.models.Account;
import com.example.travel.models.Passport;
import com.example.travel.repositories.AccountRepository;
import com.example.travel.repositories.PassportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/travel/user")
public class ProfileController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PassportRepository passportRepository;

    @GetMapping("/profile")
    public String home(Model model, Passport passport){
        Random random = new Random();
        String image = "/images/" + random.nextInt(1,4) + ".jpg";
        model.addAttribute("image", image);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        List<Account> account = accountRepository.findByUsername(currentPrincipalName);
        model.addAttribute("user", account.get(0));

        try{
            Passport pass = account.get(0).getPassport();
            if (pass != null){
                model.addAttribute("passport", pass);
                return "user/view/profilepassport";
            }
            else
                return "user/view/profile";
        }
        catch (Exception ex){

            return "user/view/profile";
        }
    }

    @PostMapping("/profile")
    public String home(@Valid Passport passport, BindingResult bindingResult, Model model){
        Random random = new Random();
        String image = "/images/" + random.nextInt(1,4) + ".jpg";
        model.addAttribute("image", image);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        List<Account> account = accountRepository.findByUsername(currentPrincipalName);
        model.addAttribute("user", account.get(0));

        if (bindingResult.hasErrors()){
            model.addAttribute("passport", passport);
            return "user/view/profile";
        }
        else {
            passport.setAccount(account.get(0));
            passportRepository.save(passport);
            return "redirect:/travel/user/profile";
        }
    }
}
