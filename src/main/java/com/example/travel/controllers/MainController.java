package com.example.travel.controllers;

import com.example.travel.models.Account;
import com.example.travel.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/")
    public String main(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        switch (currentPrincipalName){
            case "anonymousUser" -> {
                return "redirect:travel/user/home";
            }
            default -> {
                List<Account> account = accountRepository.findByUsername(currentPrincipalName);
                if (account.get(0).getRole().getId() == 1) {
                    return "redirect:travel/admin/home";
                }
                else {
                    return "redirect:travel/user/select";
                }
            }
        }
    }
}
