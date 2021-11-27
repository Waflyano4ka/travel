package com.example.travel.controllers.admin.crud;

import com.example.travel.models.City;
import com.example.travel.models.Country;
import com.example.travel.repositories.CityRepository;
import com.example.travel.repositories.CountryRepository;
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
public class CityController {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CountryRepository countryRepository;

    @GetMapping("/city")
    public String getData(Model model){
        Iterable<City> cities = cityRepository.findAll();
        model.addAttribute("cities", cities);

        return "admin/tables/city";
    }

    @PostMapping("/city")
    public String getDataSearch(
            @RequestParam String search,
            Model model){

        List<City> cities = cityRepository.findByNameContaining(search);
        model.addAttribute("cities", cities);
        model.addAttribute("search", search);

        return "admin/tables/city";
    }

    @GetMapping("/city/add")
    public String addData(City city, Model model){

        Iterable<Country> countries = countryRepository.findAll();
        model.addAttribute("elements", countries);

        return "admin/add/city";
    }

    @PostMapping("/city/add")
    public String addData(@Valid City city, BindingResult bindingResult, Model model){

        Iterable<Country> countries = countryRepository.findAll();
        model.addAttribute("elements", countries);

        if (bindingResult.hasErrors()){
            model.addAttribute("city", city);
            return "admin/add/city";
        }
        List<City> res = cityRepository.findByName(city.getName());
        if (res.size()>0){
            ObjectError error = new ObjectError("name", "Имя уже существует");
            bindingResult.addError(error);
            model.addAttribute("city", city);
            return "admin/add/city";
        }
        else {
            cityRepository.save(city);
            return "redirect:/travel/admin/city";
        }
    }

    @GetMapping("/city/{id}/edit")
    public String addData(City city, @PathVariable(value = "id") long id, Model model){
        Iterable<Country> countries = countryRepository.findAll();
        model.addAttribute("elements", countries);

        city = cityRepository.findById(id).orElseThrow();
        model.addAttribute("city", city);

        return "admin/edit/city";
    }

    @PostMapping("/city/{id}/edit")
    public String addData(@Valid City city, BindingResult bindingResult, @PathVariable(value = "id") long id, Model model){
        Iterable<Country> countries = countryRepository.findAll();
        model.addAttribute("elements", countries);

        if (bindingResult.hasErrors()){
            model.addAttribute("city", city);
            return "admin/edit/city";
        }
        List<City> res = cityRepository.findByName(city.getName());
        if (res.size()>0){
            if (res.get(0).getId() == id){

            }
            else {
                ObjectError error = new ObjectError("name", "Имя уже существует");
                bindingResult.addError(error);
                model.addAttribute("city", city);
                return "admin/edit/city";
            }
        }
        cityRepository.save(city);
        return "redirect:/travel/admin/city";
    }

    @GetMapping("/city/{id}")
    public String detailsData(City city, @PathVariable(value = "id") long id, Model model){
        Iterable<Country> countries = countryRepository.findAll();
        model.addAttribute("elements", countries);

        city = cityRepository.findById(id).orElseThrow();
        model.addAttribute("city", city);

        return "admin/detail/city";
    }

    @GetMapping("/city/{id}/delete")
    public String deleteData(City city, @PathVariable(value = "id") long id, Model model){
        city = cityRepository.findById(id).orElseThrow();
        cityRepository.delete(city);

        return "redirect:/travel/admin/city";
    }
}
