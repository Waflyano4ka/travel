package com.example.travel.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Поле обязательно для заполнения")
    private String name, menu;
    @Min(value = 0, message = "Значение не может быть отрицательным")
    @Max(value = 10, message = "Значение не должно превышать 10")
    @NotNull(message = "Поле обязательно для заполнения")
    private Integer rating;
    @NotNull(message = "Поле обязательно для заполнения")
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private City city;

    public Restaurant(String name, String menu, Integer rating, City city) {
        this.name = name;
        this.menu = menu;
        this.rating = rating;
        this.city = city;
    }

    public Restaurant() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}

