package com.example.travel.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Поле обязательно для заполнения")
    @Size(min = 4, max = 4, message = "Серия состоит из 4 символов")
    private String seria;
    @NotEmpty(message = "Поле обязательно для заполнения")
    @Size(min = 6, max = 6, message = "Серия состоит из 6 символов")
    private String number;
    @NotNull(message = "Поле обязательно для заполнения")
    @Past(message = "Дата регистрации не должна быть в будущем")
    private Date date;
    @NotEmpty(message = "Поле обязательно для заполнения")
    private String address, surname, name;
    private String secondName;
    @OneToOne(optional = true, cascade = CascadeType.ALL)
    private Account account;

    public Passport(String seria, String number, Date date, String address, String surname, String name, String secondName, Account account) {
        this.seria = seria;
        this.number = number;
        this.date = date;
        this.address = address;
        this.surname = surname;
        this.name = name;
        this.secondName = secondName;
        this.account = account;
    }

    public Passport() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeria() {
        return seria;
    }

    public void setSeria(String seria) {
        this.seria = seria;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
