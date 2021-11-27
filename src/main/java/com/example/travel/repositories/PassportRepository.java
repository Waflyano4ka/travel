package com.example.travel.repositories;

import com.example.travel.models.Hotel;
import com.example.travel.models.Passport;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PassportRepository extends CrudRepository<Passport, Long> {
    List<Passport> findBySurnameContaining(String name);
    List<Passport> findBySurname(String name);
}
