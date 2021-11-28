package com.example.travel.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Optional;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Поле обязательно для заполнения")
    @Email(message = "Введено неверное значение")
    private String username;
    @NotEmpty(message = "Поле обязательно для заполнения")
    private String password;
    private boolean active;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Role role;
    @OneToOne(optional = true, mappedBy = "account")
    private Passport passport;
    private Long city_id;

    @ManyToMany
    @JoinTable(name = "booking",
        joinColumns = @JoinColumn(name = "account_id"),
        inverseJoinColumns = @JoinColumn(name = "hotel_id"))
    private List<Hotel> hotels;

    public Account(String username, String password, boolean active, Role role) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.role = role;
    }

    public Account() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public Long getCity_id() {
        return city_id;
    }

    public void setCity_id(Long city_id) {
        this.city_id = city_id;
    }
}
