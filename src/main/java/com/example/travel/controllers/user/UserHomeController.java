package com.example.travel.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;

@Controller
@RequestMapping("/travel/user")
public class UserHomeController {

    @GetMapping("/home")
    public String home(Model model){
        Random random = new Random();
        String image = "/images/" + random.nextInt(1,4) + ".jpg";
        model.addAttribute("image", image);

        return "user/view/home";
    }
}