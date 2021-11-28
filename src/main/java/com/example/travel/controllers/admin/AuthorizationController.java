package com.example.travel.controllers.admin;

import com.example.travel.models.Account;
import com.example.travel.repositories.AccountRepository;
import com.example.travel.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/travel/admin")
public class AuthorizationController {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String home(Model model){
        Random random = new Random();
        String image = "/images/" + random.nextInt(1,4) + ".jpg";
        model.addAttribute("image", image);

        return "admin/view/login";
    }

    @GetMapping("/registration")
    public String registration(Account account, Model model){
        Random random = new Random();
        String image = "/images/" + random.nextInt(1,4) + ".jpg";
        model.addAttribute("image", image);

        return "user/view/registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid Account account, BindingResult bindingResult, Model model){
        Random random = new Random();
        String image = "/images/" + random.nextInt(1,4) + ".jpg";
        model.addAttribute("image", image);

        if (bindingResult.hasErrors()){
            model.addAttribute("account", account);
            return "user/view/registration";
        }
        List<Account> res = accountRepository.findByUsername(account.getUsername());
        if (res.size()>0){
            ObjectError error = new ObjectError("name", "Имя уже существует");
            bindingResult.addError(error);
            model.addAttribute("account", account);
            return "user/view/registration";
        }
        else {
            account.setRole(roleRepository.findById((long)2).orElseThrow());
            account.setActive(true);
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            accountRepository.save(account);
            return "redirect:/travel/admin/login";
        }
    }
}
