package com.example.travel.controllers.admin.crud;

import com.example.travel.models.Country;
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
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    @GetMapping("/country")
    public String getData(Model model){
        Iterable<Country> countries = countryRepository.findAll();
        model.addAttribute("countries", countries);

        return "admin/tables/country";
    }

    @PostMapping("/country")
    public String getDataSearch(
            @RequestParam String search,
            Model model){

        List<Country> countries = countryRepository.findByNameContaining(search);
        model.addAttribute("countries", countries);
        model.addAttribute("search", search);

        return "admin/tables/country";
    }

    @GetMapping("/country/add")
    public String addData(Country country){

        return "admin/add/country";
    }

    @PostMapping("/country/add")
    public String addData(@Valid Country country, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("country", country);
            return "admin/add/country";
        }
        List<Country> res = countryRepository.findByName(country.getName());
        if (res.size()>0){
            ObjectError error = new ObjectError("name", "Имя уже существует");
            bindingResult.addError(error);
            model.addAttribute("country", country);
            return "admin/add/country";
        }
        else {
            countryRepository.save(country);
            return "redirect:/travel/admin/country";
        }
    }

    @GetMapping("/country/{id}/edit")
    public String addData(Country country, @PathVariable(value = "id") long id, Model model){
        country = countryRepository.findById(id).orElseThrow();
        model.addAttribute("country", country);

        return "admin/edit/country";
    }

    @PostMapping("/country/{id}/edit")
    public String addData(@Valid Country country, BindingResult bindingResult, @PathVariable(value = "id") long id, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("country", country);
            return "admin/edit/country";
        }
        List<Country> res = countryRepository.findByName(country.getName());
        if (res.size()>0){
            if (res.get(0).getId() == id){

            }
            else {
                ObjectError error = new ObjectError("name", "Имя уже существует");
                bindingResult.addError(error);
                model.addAttribute("country", country);
                return "admin/edit/country";
            }
        }
        countryRepository.save(country);
        return "redirect:/travel/admin/country";
    }

    @GetMapping("/country/{id}")
    public String detailsData(Country country, @PathVariable(value = "id") long id, Model model){
        country = countryRepository.findById(id).orElseThrow();
        model.addAttribute("country", country);

        return "admin/detail/country";
    }

    @GetMapping("/country/{id}/delete")
    public String deleteData(Country country, @PathVariable(value = "id") long id, Model model){
        country = countryRepository.findById(id).orElseThrow();
        countryRepository.delete(country);

        return "redirect:/travel/admin/country";
    }
}