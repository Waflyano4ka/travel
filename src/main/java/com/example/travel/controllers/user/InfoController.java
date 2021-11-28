package com.example.travel.controllers.user;

import com.example.travel.models.Account;
import com.example.travel.models.Passport;
import com.example.travel.repositories.AccountRepository;
import com.example.travel.repositories.CityRepository;
import com.example.travel.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/travel/user")
public class InfoController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping("/restaurant")
    public String restaurantHome(Model model, Passport passport){
        Random random = new Random();
        String image = "/images/" + random.nextInt(1,4) + ".jpg";
        model.addAttribute("image", image);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        List<Account> account = accountRepository.findByUsername(currentPrincipalName);
        model.addAttribute("user", account.get(0));

        model.addAttribute("city", cityRepository.findById(account.get(0).getCity_id()).orElseThrow());
        model.addAttribute("objects", cityRepository.findById(account.get(0).getCity_id()).orElseThrow().getRestaurants());

        return "user/info/restaurant";
    }

    @GetMapping("/hotel")
    public String hotelHome(Model model, Passport passport){
        Random random = new Random();
        String image = "/images/" + random.nextInt(1,4) + ".jpg";
        model.addAttribute("image", image);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        List<Account> account = accountRepository.findByUsername(currentPrincipalName);
        model.addAttribute("user", account.get(0));

        model.addAttribute("city", cityRepository.findById(account.get(0).getCity_id()).orElseThrow());
        model.addAttribute("objects", cityRepository.findById(account.get(0).getCity_id()).orElseThrow().getHotels());

        return "user/info/hotel";
    }

    @GetMapping("/hotel/{id}")
    public String detailsData(@PathVariable(value = "id") long id, Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        List<Account> account = accountRepository.findByUsername(currentPrincipalName);
        account.get(0).getHotels().add(hotelRepository.findById(id).orElseThrow());
        accountRepository.save(account.get(0));

        return "redirect:/travel/user/hotel/";
    }

    @GetMapping("/excursion")
    public String excursionHome(Model model, Passport passport){
        Random random = new Random();
        String image = "/images/" + random.nextInt(1,4) + ".jpg";
        model.addAttribute("image", image);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        List<Account> account = accountRepository.findByUsername(currentPrincipalName);
        model.addAttribute("user", account.get(0));

        model.addAttribute("city", cityRepository.findById(account.get(0).getCity_id()).orElseThrow());
        model.addAttribute("objects", cityRepository.findById(account.get(0).getCity_id()).orElseThrow().getExcursions());

        return "user/info/excursion";
    }
}
