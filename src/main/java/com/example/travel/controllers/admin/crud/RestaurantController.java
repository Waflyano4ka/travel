package com.example.travel.controllers.admin.crud;

import com.example.travel.models.City;
import com.example.travel.models.Country;
import com.example.travel.models.Restaurant;
import com.example.travel.repositories.CityRepository;
import com.example.travel.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/travel/admin")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private CityRepository cityRepository;

    @GetMapping("/restaurant")
    public String getData(Model model){
        Iterable<Restaurant> restaurants = restaurantRepository.findAll();
        model.addAttribute("restaurants", restaurants);

        return "admin/tables/restaurant";
    }

    @PostMapping("/restaurant")
    public String getDataSearch(
            @RequestParam String search,
            Model model){

        List<Restaurant> restaurants = restaurantRepository.findByNameContaining(search);
        model.addAttribute("restaurants", restaurants);
        model.addAttribute("search", search);

        return "admin/tables/restaurant";
    }

    @GetMapping("/restaurant/add")
    public String addData(Restaurant restaurant, Model model){

        Iterable<City> cities = cityRepository.findAll();
        model.addAttribute("elements", cities);

        return "admin/add/restaurant";
    }

    @PostMapping("/restaurant/add")
    public String addData(@Valid Restaurant restaurant, BindingResult bindingResult, Model model){

        Iterable<City> cities = cityRepository.findAll();
        model.addAttribute("elements", cities);

        if (bindingResult.hasErrors()){
            model.addAttribute("restaurant", restaurant);
            return "admin/add/restaurant";
        }
        else {
            restaurantRepository.save(restaurant);
            return "redirect:/travel/admin/restaurant";
        }
    }

    @GetMapping("/restaurant/{id}/edit")
    public String addData(Restaurant restaurant, @PathVariable(value = "id") long id, Model model){
        Iterable<City> cities = cityRepository.findAll();
        model.addAttribute("elements", cities);

        restaurant = restaurantRepository.findById(id).orElseThrow();
        model.addAttribute("restaurant", restaurant);

        return "admin/edit/restaurant";
    }

    @PostMapping("/restaurant/{id}/edit")
    public String addData(@Valid Restaurant restaurant, BindingResult bindingResult, @PathVariable(value = "id") long id, Model model){
        Iterable<City> cities = cityRepository.findAll();
        model.addAttribute("elements", cities);

        if (bindingResult.hasErrors()){
            model.addAttribute("restaurant", restaurant);
            return "admin/edit/restaurant";
        }
        restaurantRepository.save(restaurant);
        return "redirect:/travel/admin/restaurant";
    }
}
