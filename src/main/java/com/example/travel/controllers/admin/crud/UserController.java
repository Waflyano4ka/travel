package com.example.travel.controllers.admin.crud;

import com.example.travel.models.Account;
import com.example.travel.repositories.AccountRepository;
import com.example.travel.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/travel/admin")
public class UserController {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/user")
    public String getData(Model model){
        Iterable<Account> users = roleRepository.findById((long)2).orElseThrow().getAccounts();
        model.addAttribute("users", users);

        return "admin/tables/user";
    }

    @PostMapping("/user")
    public String getDataSearch(
            @RequestParam String search,
            Model model){

        Iterable<Account> users = accountRepository.findByUsernameContaining(search);
        ArrayList<Account> searchUsers = new ArrayList<Account>();
        for(Account user : users) {
            if (user.getRole().getId() == 2) {
                searchUsers.add(user);
            }
        }
        model.addAttribute("users", searchUsers);
        model.addAttribute("search", search);

        return "admin/tables/user";
    }
}
