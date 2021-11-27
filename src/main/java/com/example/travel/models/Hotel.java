package com.example.travel.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Поле обязательно для заполнения")
    private String name;
    @Min(value = 0, message = "Значение не может быть отрицательным")
    @Max(value = 10, message = "Значение не должно превышать 10")
    @NotNull(message = "Поле обязательно для заполнения")
    private Integer rating;
    @Min(value = 0, message = "Значение не может быть отрицательным")
    @NotNull(message = "Поле обязательно для заполнения")
    private Double cost;
    @NotNull(message = "Поле обязательно для заполнения")
    @ManyToOne(optional = false)
    private City city;

    @ManyToMany
    @JoinTable(name = "booking",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id"))
    private List<Account> accounts;

    public Hotel(String name, Integer rating, Double cost, City city) {
        this.name = name;
        this.rating = rating;
        this.cost = cost;
        this.city = city;
    }

    public Hotel() {

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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
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

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
