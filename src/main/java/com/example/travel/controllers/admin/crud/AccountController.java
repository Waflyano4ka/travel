package com.example.travel.controllers.admin.crud;

import com.example.travel.models.Account;
import com.example.travel.models.City;
import com.example.travel.models.Country;
import com.example.travel.models.Role;
import com.example.travel.repositories.AccountRepository;
import com.example.travel.repositories.CountryRepository;
import com.example.travel.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/travel/admin")
public class AccountController {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/account")
    public String getData(Model model){
        Iterable<Account> accounts = roleRepository.findById((long)1).orElseThrow().getAccounts();
        model.addAttribute("accounts", accounts);

        return "admin/tables/account";
    }

    @PostMapping("/account")
    public String getDataSearch(
            @RequestParam String search,
            Model model){

        Iterable<Account> accounts = accountRepository.findByUsernameContaining(search);
        ArrayList<Account> searchAccounts = new ArrayList<Account>();
        for(Account account : accounts) {
            if (account.getRole().getId() == 1) {
                searchAccounts.add(account);
            }
        }
        model.addAttribute("accounts", searchAccounts);
        model.addAttribute("search", search);

        return "admin/tables/account";
    }

    @GetMapping("/account/add")
    public String addData(Account account){

        return "admin/add/account";
    }

    @PostMapping("/account/add")
    public String addData(@Valid Account account, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("account", account);
            return "admin/add/account";
        }
        List<Account> res = accountRepository.findByUsername(account.getUsername());
        if (res.size()>0){
            ObjectError error = new ObjectError("name", "Имя уже существует");
            bindingResult.addError(error);
            model.addAttribute("account", account);
            return "admin/add/account";
        }
        else {
            account.setRole(roleRepository.findById((long)1).orElseThrow());
            account.setActive(true);
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            accountRepository.save(account);
            return "redirect:/travel/admin/account";
        }
    }

    @GetMapping("/account/{id}/edit")
    public String addData(Account account, @PathVariable(value = "id") long id, Model model){

        account = accountRepository.findById(id).orElseThrow();
        model.addAttribute("account", account);

        return "admin/edit/account";
    }

    @PostMapping("/account/{id}/edit")
    public String addData(@Valid Account account, BindingResult bindingResult, @PathVariable(value = "id") long id, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("account", account);
            return "admin/edit/account";
        }
        List<Account> res = accountRepository.findByUsername(account.getUsername());
        if (res.size()>0){
            if (res.get(0).getId() == id){

            }
            else {
                ObjectError error = new ObjectError("name", "Имя уже существует");
                bindingResult.addError(error);
                model.addAttribute("account", account);
                return "admin/edit/account";
            }
        }
        account.setRole(roleRepository.findById((long)1).orElseThrow());
        if (account.getPassword().length() < 13){
            account.setPassword(passwordEncoder.encode(account.getPassword()));
        }
        accountRepository.save(account);
        return "redirect:/travel/admin/account";
    }

    @GetMapping("/user/{id}/edit")
    public String addDataU(Account user, @PathVariable(value = "id") long id, Model model){

        user = accountRepository.findById(id).orElseThrow();
        model.addAttribute("account", user);

        return "admin/edit/user";
    }

    @PostMapping("/user/{id}/edit")
    public String addDataU(@Valid Account user, BindingResult bindingResult, @PathVariable(value = "id") long id, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("account", user);
            return "admin/edit/user";
        }
        List<Account> res = accountRepository.findByUsername(user.getUsername());
        if (res.size()>0){
            if (res.get(0).getId() == id){

            }
            else {
                ObjectError error = new ObjectError("name", "Имя уже существует");
                bindingResult.addError(error);
                model.addAttribute("account", user);
                return "admin/edit/user";
            }
        }
        user.setRole(roleRepository.findById((long)2).orElseThrow());
        if (user.getPassword().length() < 13){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        user.setHotels(accountRepository.findById(id).orElseThrow().getHotels());
        accountRepository.save(user);
        return "redirect:/travel/admin/user";
    }
}
