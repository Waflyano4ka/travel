package com.example.travel.controllers.user;

import com.example.travel.models.Account;
import com.example.travel.models.City;
import com.example.travel.repositories.AccountRepository;
import com.example.travel.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/travel/user")
public class SelectController {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/select")
    public String home(Model model){
        Random random = new Random();
        String image = "/images/" + random.nextInt(1,4) + ".jpg";
        model.addAttribute("image", image);

        Iterable<City> cities = cityRepository.findAll();
        model.addAttribute("elements", cities);

        return "user/view/select";
    }

    @PostMapping("/select")
    public String home(Model model, @RequestParam String city){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        List<Account> account = accountRepository.findByUsername(currentPrincipalName);
        account.get(0).setCity_id(Long.parseLong(city));
        accountRepository.save(account.get(0));

        return "redirect:/travel/user/hotel";
    }
}
