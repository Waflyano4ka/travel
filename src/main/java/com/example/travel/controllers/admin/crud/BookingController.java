package com.example.travel.controllers.admin.crud;

import com.example.travel.models.Account;
import com.example.travel.models.City;
import com.example.travel.models.Country;
import com.example.travel.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/travel/admin")
public class BookingController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/booking")
    public String getData(Model model){
        Iterable<Account> accounts = accountRepository.findAll();
        model.addAttribute("accounts", accounts);

        return "admin/tables/booking";
    }

    @PostMapping("/booking")
    public String getDataSearch(
            @RequestParam String search,
            Model model){

        List<Account> accounts = accountRepository.findByUsernameContaining(search);
        model.addAttribute("accounts", accounts);
        model.addAttribute("search", search);

        return "admin/tables/booking";
    }

    @GetMapping("/booking/{id}")
    public String detailsData(Account account, @PathVariable(value = "id") long id, Model model){
        account = accountRepository.findById(id).orElseThrow();
        model.addAttribute("account", account);

        return "admin/detail/booking";
    }
}
