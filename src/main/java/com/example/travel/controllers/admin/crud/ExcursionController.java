package com.example.travel.controllers.admin.crud;

import com.example.travel.models.*;
import com.example.travel.repositories.CityRepository;
import com.example.travel.repositories.ExcursionRepository;
import com.example.travel.repositories.TypeExcursionRepository;
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
public class ExcursionController {

    @Autowired
    private ExcursionRepository excursionRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private TypeExcursionRepository typeExcursionRepository;

    @GetMapping("/excursion")
    public String getData(Model model){
        Iterable<Excursion> excursions = excursionRepository.findAll();
        model.addAttribute("excursions", excursions);

        return "admin/tables/excursion";
    }

    @PostMapping("/excursion")
    public String getDataSearch(
            @RequestParam String search,
            Model model){

        List<Excursion> excursions = excursionRepository.findByNameContaining(search);
        model.addAttribute("excursions", excursions);
        model.addAttribute("search", search);

        return "admin/tables/excursion";
    }

    @GetMapping("/excursion/add")
    public String addData(Excursion excursion, Model model){

        Iterable<City> cities = cityRepository.findAll();
        model.addAttribute("elements", cities);
        Iterable<TypeExcursion> typeExcursions = typeExcursionRepository.findAll();
        model.addAttribute("elements2", typeExcursions);

        return "admin/add/excursion";
    }

    @PostMapping("/excursion/add")
    public String addData(@Valid Excursion excursion, BindingResult bindingResult, Model model){

        Iterable<City> cities = cityRepository.findAll();
        model.addAttribute("elements", cities);
        Iterable<TypeExcursion> typeExcursions = typeExcursionRepository.findAll();
        model.addAttribute("elements2", typeExcursions);

        if (bindingResult.hasErrors()){
            model.addAttribute("excursion", excursion);
            return "admin/add/excursion";
        }
        List<Excursion> res = excursionRepository.findByName(excursion.getName());
        if (res.size()>0){
            ObjectError error = new ObjectError("name", "Имя уже существует");
            bindingResult.addError(error);
            model.addAttribute("excursion", excursion);
            return "admin/add/excursion";
        }
        else {
            excursionRepository.save(excursion);
            return "redirect:/travel/admin/excursion";
        }
    }

    @GetMapping("/excursion/{id}/edit")
    public String addData(Excursion excursion, @PathVariable(value = "id") long id, Model model){
        Iterable<City> cities = cityRepository.findAll();
        model.addAttribute("elements", cities);
        Iterable<TypeExcursion> typeExcursions = typeExcursionRepository.findAll();
        model.addAttribute("elements2", typeExcursions);

        excursion = excursionRepository.findById(id).orElseThrow();
        model.addAttribute("excursion", excursion);

        return "admin/edit/excursion";
    }

    @PostMapping("/excursion/{id}/edit")
    public String addData(@Valid Excursion excursion, BindingResult bindingResult, @PathVariable(value = "id") long id, Model model){
        Iterable<City> cities = cityRepository.findAll();
        model.addAttribute("elements", cities);
        Iterable<TypeExcursion> typeExcursions = typeExcursionRepository.findAll();
        model.addAttribute("elements2", typeExcursions);

        if (bindingResult.hasErrors()){
            model.addAttribute("excursion", excursion);
            return "admin/edit/excursion";
        }
        List<Excursion> res = excursionRepository.findByName(excursion.getName());
        if (res.size()>0){
            if (res.get(0).getId() == id){

            }
            else {
                ObjectError error = new ObjectError("name", "Имя уже существует");
                bindingResult.addError(error);
                model.addAttribute("excursion", excursion);
                return "admin/edit/excursion";
            }
        }
        excursionRepository.save(excursion);
        return "redirect:/travel/admin/excursion";
    }
}
