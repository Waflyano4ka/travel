package com.example.travel.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Поле обязательно для заполнения")
    private String name;
    @NotNull(message = "Поле обязательно для заполнения")
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Country country;
    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private Collection<Restaurant> restaurants;
    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private Collection<Excursion> excursions;
    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private Collection<Hotel> hotels;

    public City(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public City() {

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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Collection<Restaurant> getRestaurants() { return restaurants; }

    public Collection<Excursion> getExcursions() { return excursions; }

    public Collection<Hotel> getHotels() { return hotels; }
}
