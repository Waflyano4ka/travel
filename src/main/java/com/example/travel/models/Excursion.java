package com.example.travel.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Excursion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Поле обязательно для заполнения")
    private String name, description;
    @Min(value = 0, message = "Значение не может быть отрицательным")
    @NotNull(message = "Поле обязательно для заполнения")
    private Double cost;
    @NotNull(message = "Поле обязательно для заполнения")
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private City city;
    @NotNull(message = "Поле обязательно для заполнения")
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private TypeExcursion typeExcursion;

    public Excursion(String name, String description, Double cost, City city, TypeExcursion typeExcursion) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.city = city;
        this.typeExcursion = typeExcursion;
    }

    public Excursion() {

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public TypeExcursion getTypeExcursion() {
        return typeExcursion;
    }

    public void setTypeExcursion(TypeExcursion typeExcursion) {
        this.typeExcursion = typeExcursion;
    }
}
