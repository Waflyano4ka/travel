package com.example.travel.controllers.admin.crud;

import com.example.travel.models.*;
import com.example.travel.repositories.CityRepository;
import com.example.travel.repositories.HotelRepository;
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
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private CityRepository cityRepository;

    @GetMapping("/hotel")
    public String getData(Model model){
        Iterable<Hotel> hotels = hotelRepository.findAll();
        model.addAttribute("hotels", hotels);

        return "admin/tables/hotel";
    }

    @PostMapping("/hotel")
    public String getDataSearch(
            @RequestParam String search,
            Model model){

        List<Hotel> hotels = hotelRepository.findByNameContaining(search);
        model.addAttribute("hotels", hotels);
        model.addAttribute("search", search);

        return "admin/tables/hotel";
    }

    @GetMapping("/hotel/add")
    public String addData(Hotel hotel, Model model){

        Iterable<City> cities = cityRepository.findAll();
        model.addAttribute("elements", cities);

        return "admin/add/hotel";
    }

    @PostMapping("/hotel/add")
    public String addData(@Valid Hotel hotel, BindingResult bindingResult, Model model){

        Iterable<City> cities = cityRepository.findAll();
        model.addAttribute("elements", cities);

        if (bindingResult.hasErrors()){
            model.addAttribute("hotel", hotel);
            return "admin/add/hotel";
        }
        else {
            hotelRepository.save(hotel);
            return "redirect:/travel/admin/hotel";
        }
    }

    @GetMapping("/hotel/{id}/edit")
    public String addData(Hotel hotel, @PathVariable(value = "id") long id, Model model){
        Iterable<City> cities = cityRepository.findAll();
        model.addAttribute("elements", cities);

        hotel = hotelRepository.findById(id).orElseThrow();
        model.addAttribute("hotel", hotel);

        return "admin/edit/hotel";
    }

    @PostMapping("/hotel/{id}/edit")
    public String addData(@Valid Hotel hotel, BindingResult bindingResult, @PathVariable(value = "id") long id, Model model){
        Iterable<City> cities = cityRepository.findAll();
        model.addAttribute("elements", cities);

        if (bindingResult.hasErrors()){
            model.addAttribute("hotel", hotel);
            return "admin/edit/hotel";
        }
        hotelRepository.save(hotel);
        return "redirect:/travel/admin/hotel";
    }

    @GetMapping("/hotel/{id}")
    public String detailsData(Hotel hotel, @PathVariable(value = "id") long id, Model model){
        Iterable<City> cities = cityRepository.findAll();
        model.addAttribute("elements", cities);

        hotel = hotelRepository.findById(id).orElseThrow();
        model.addAttribute("hotel", hotel);

        return "admin/detail/hotel";
    }

    @GetMapping("/hotel/{id}/delete")
    public String deleteData(Hotel hotel, @PathVariable(value = "id") long id, Model model){
        hotel = hotelRepository.findById(id).orElseThrow();
        hotelRepository.delete(hotel);

        return "redirect:/travel/admin/hotel";
    }
}
