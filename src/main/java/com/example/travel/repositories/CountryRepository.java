package com.example.travel.repositories;

import com.example.travel.models.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CountryRepository extends CrudRepository<Country, Long> {
    List<Country> findByNameContaining(String name);
    List<Country> findByName(String name);
}
