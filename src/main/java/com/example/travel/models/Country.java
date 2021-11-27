package com.example.travel.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Поле обязательно для заполнения")
    private String name;

    @OneToMany(mappedBy = "country", fetch = FetchType.EAGER)
    private Collection<City> cities;

    public Country(String name) {
        this.name = name;
    }

    public Country() {

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

    public Collection<City> getCities() {
        return cities;
    }
}
