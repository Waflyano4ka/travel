package com.example.travel.controllers.admin;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/travel/admin")
public class HomeController {

    @GetMapping("/home")
    public String home(Model model){

        return "admin/view/home";
    }
}
